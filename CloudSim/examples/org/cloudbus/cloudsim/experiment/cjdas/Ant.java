package org.cloudbus.cloudsim.experiment.cjdas;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Ant {

	private Integer id;
	private Double Q_0 = 0.8;
	private Double Q_1 = 0.9;
	private Double alpha = 1.0;
	private Double beta = 2.0;
	private Double rou = 0.95;
	/**
	 * 待的虚拟机列表
	 */
	public List<VMACO> vmlist = new ArrayList<>();
	/**
	 * 已放置的虚拟机列表
	 */
	public List<VMACO> loadvmlist = new ArrayList<>();
	/**
	 * 待访问的物理机
	 */
	public Map<String, HostACO> hostmap = new HashMap<String, HostACO>();
	/**
	 * 已放置的方案
	 */
	public final List<String> programme = new ArrayList<String>();
	public Map<String, Integer> pheromoneSituation = new HashMap<String, Integer>();
	public Map<String, Integer> pS = new HashMap<String, Integer>();
	/**
	 * 得到的解
	 */
	public Double solution;
	public Double sla_solution;
	public Double power_solution;
	public Double balance_solution;

	public Ant(Integer id, List<VMACO> vmlist, Map<String, HostACO> hostMap,
			Map<String, Integer> pS) {

		setId(id);
		setVmlist(vmlist);
		setHostmap(hostMap);
		setPheromoneSituation(pS);
		setpS(pS);
	}

	/**
	 * 蚂蚁遍历
	 */
	public void antErgodic() {

		// VMACO vm = vmlist.get(0);
		// 存放虚拟机与物理机xingema值的哈希表
		@SuppressWarnings("all")
		Iterator<VMACO> ite = vmlist.iterator();
		while (ite.hasNext()) {

			VMACO vm = ite.next();
			Map<String, Double> xingema_high_Map = new HashMap<String, Double>();
			Map<String, Double> xingema_low_Map = new HashMap<String, Double>();
			for (String hostKey : hostmap.keySet()) {

				HostACO host = hostmap.get(hostKey);
				// 判断当前循环中的物理机能否被该虚拟机放置
				if (!canLoad(vm, host)) {
					continue;
				}
				MathContext mc = new MathContext(2);
				Double alreadyLoadMips = host.getMips() - host.getCurrentMips();
				Double alreadyLoadRam = (double) (host.getCurrentRam() - host
						.getCurrentRam());
				Double alreadyLoadBw = host.getBw() - host.getCurrentBw();
				// SLA履约率启发式因子的值
				Double yita_sla = 0.0;
				try {
					yita_sla = BigDecimal
							.valueOf(1)
							.divide(BigDecimal.valueOf(Math.abs(host.getMips()
									- (vm.getMips() + alreadyLoadMips))), 2,
									BigDecimal.ROUND_HALF_UP).doubleValue();
				} catch (Exception e) {
					System.out.println(vm);
					System.out.println(host);
				}
				
				// 能源消耗启发式因子的值
				Double yita_power = BigDecimal
						.valueOf(0.33)
						.multiply(
								BigDecimal.valueOf(BigDecimal
										.valueOf(vm.getMips())
										.divide(BigDecimal.valueOf(host
												.getMips()), 2,
												BigDecimal.ROUND_HALF_UP)
										.doubleValue()
										+ BigDecimal
												.valueOf(vm.getBw())
												.divide(BigDecimal.valueOf(host
														.getBw()),
														2,
														BigDecimal.ROUND_HALF_UP)
												.doubleValue()
										+ BigDecimal
												.valueOf(vm.getRam())
												.divide(BigDecimal.valueOf(host
														.getRam()),
														2,
														BigDecimal.ROUND_HALF_UP)
												.doubleValue()), mc)
						.doubleValue();
				// 负载均衡率启发式因子的值
				Double yita_balance = 0.5 * (BigDecimal
						.valueOf(vm.getMips())
						.multiply(BigDecimal.valueOf(host.getMips()), mc)
						.add(BigDecimal.valueOf(vm.getRam()).multiply(
								BigDecimal.valueOf(host.getRam()), mc), mc)
						.add(BigDecimal.valueOf(vm.getBw()).multiply(
								BigDecimal.valueOf(host.getBw()), mc), mc)
						.divide(BigDecimal
								.valueOf(
										Math.sqrt(BigDecimal
												.valueOf(vm.getMips())
												.multiply(
														BigDecimal.valueOf(vm
																.getRam()), mc)
												.multiply(
														BigDecimal.valueOf(vm
																.getBw()), mc)
												.doubleValue())).multiply(
										BigDecimal.valueOf(Math.sqrt(BigDecimal
												.valueOf(host.getMips())
												.multiply(
														BigDecimal.valueOf(host
																.getRam()), mc)
												.multiply(
														BigDecimal.valueOf(host
																.getBw()), mc)
												.doubleValue())), mc), 2,
								BigDecimal.ROUND_HALF_UP).doubleValue() + 1);
				String key = vm.getVmm() + "," + host.getHostname();
				if (Pheromone.getPheromone(key) == null) {
					System.out.println("Check Pheromone:" + key);
				}
				// double Q = Math.random();
				// 计算xingema
				// check Pheromone
				
				Double d = Pheromone.getPheromone(key);
				Double xingema_high_ij = BigDecimalUtil.mul(BigDecimalUtil.mul(
						BigDecimalUtil.mul(
								Math.pow(Pheromone.getPheromone(key), alpha),
								Math.pow(yita_sla, 0.4 * beta)), Math.pow(
								yita_power, 0.3 * beta)), Math.pow(
						yita_balance, 0.3 * beta));

				// 计算xingema_
				Double xingema_low_ij = BigDecimalUtil.mul(
						BigDecimalUtil.mul(
								BigDecimalUtil.mul(
										Math.pow(
												BigDecimalUtil.div(1, Pheromone
														.getPheromone(key), 4),
												alpha), Math.pow(yita_sla,
												0.4 * beta)), Math.pow(
										yita_power, 0.3 * beta)), Math.pow(
								yita_balance, 0.3 * beta));
				xingema_low_Map.put(key, xingema_low_ij);
				xingema_high_Map.put(key, xingema_high_ij);

			}
			if (xingema_low_Map.size() == 0 || xingema_high_Map.size() == 0) {
				continue;
			}
			// 分母的计算
			double denominator_high = 0;
			double denominator_low = 0;
			for (String keyName : xingema_low_Map.keySet()) {
				denominator_low += xingema_low_Map.get(keyName);
			}
			for (String keyName : xingema_high_Map.keySet()) {
				denominator_high += xingema_high_Map.get(keyName);
			}
			Double Q = Math.random();
			if (Q > Q_0) {
				String combineOfVmAndHost = getSortedHashtableByValue(xingema_high_Map)[0]
						.getKey().toString();
				// 部署
				String[] names = combineOfVmAndHost.split(",");
				String hostName = names[1];
				HostACO host = hostmap.get(hostName);
				if (host != null) {
					host.setActive(true);
					host.setCurrentBw(BigDecimalUtil.sub(host.getCurrentBw(),
							vm.getBw()));
					host.setCurrentMips(BigDecimalUtil.sub(
							host.getCurrentMips(), vm.getMips()));
					host.setCurrentRam(BigDecimalUtil.sub(host.getCurrentRam(),
							vm.getRam()));
					host.getVmlist().add(vm);
				}
				vm.setHost(host);
				loadvmlist.add(vm);
				programme.add(vm.getVmm() + "," + host.getHostname());
				//ite.remove();
			} else if (Q >= Q_0 && Q <= Q_1) {
				Double ran = BigDecimalUtil
						.sub(Math.random(), denominator_high);
				Double value = 0.0;
				String combineOfVmAndHost = null;
				for (String key : xingema_high_Map.keySet()) {
					value += xingema_high_Map.get(key);
					if (ran >= value) {
						combineOfVmAndHost = key;
					}
				}
				// 部署
				String[] names = combineOfVmAndHost.split(",");
				String hostName = names[1];
				HostACO host = hostmap.get(hostName);
				if (host != null) {
					host.setActive(true);
					host.setCurrentBw(BigDecimalUtil.sub(host.getCurrentBw(),
							vm.getBw()));
					host.setCurrentMips(BigDecimalUtil.sub(
							host.getCurrentMips(), vm.getMips()));
					host.setCurrentRam(BigDecimalUtil.sub(host.getCurrentRam(),
							vm.getRam()));
					host.getVmlist().add(vm);
				}
				vm.setHost(host);
				loadvmlist.add(vm);
				programme.add(vm.getVmm() + "," + host.getHostname());
				//ite.remove();
			} else {
				String combineOfVmAndHost = getSortedHashtableByValueUP(xingema_low_Map)[0]
						.getKey().toString();
				// 部署
				String[] names = combineOfVmAndHost.split(",");
				String hostName = names[1];
				HostACO host = hostmap.get(hostName);
				if (host != null) {
					host.setActive(true);
					host.setCurrentBw(BigDecimalUtil.sub(host.getCurrentBw(),
							vm.getBw()));
					host.setCurrentMips(BigDecimalUtil.sub(
							host.getCurrentMips(), vm.getMips()));
					host.setCurrentRam(BigDecimalUtil.sub(host.getCurrentRam(),
							vm.getRam()));
					host.getVmlist().add(vm);
				}
				vm.setHost(host);
				loadvmlist.add(vm);
				programme.add(vm.getVmm() + "," + host.getHostname());
				//ite.remove();
			}

		}
		// 所有的虚拟机均已经放置，计算当前得到的解
		// 计算SLA履约率的值
		Double sla_assist = 0.0;
		for (String hostKey : hostmap.keySet()) {
			HostACO host = hostmap.get(hostKey);
			sla_assist += BigDecimalUtil.add(
					0.3,
					Math.log(BigDecimalUtil.sub(
							2,
							BigDecimalUtil.div(
									BigDecimalUtil.sub(host.getMips(),
											host.getCurrentMips()),
									host.getMips(), 4))));
		}
		sla_solution = BigDecimalUtil.div(sla_assist, hostmap.size(), 4);
		// 计算power的值
		Double power_assist = 0.0;
		for (String hostKey : hostmap.keySet()) {
			HostACO host = hostmap.get(hostKey);
			Double u_cpu = BigDecimalUtil.div(
					BigDecimalUtil.sub(host.getMips(), host.getCurrentMips()),
					host.getMips(), 4);
			power_assist += BigDecimalUtil.div(
					BigDecimalUtil.mul(u_cpu, 250),
					BigDecimalUtil.add(
							175,
							BigDecimalUtil.mul(0.3,
									BigDecimalUtil.mul(250, u_cpu))), 4);
		}
		power_solution = BigDecimalUtil.div(power_assist, hostmap.size(), 4);
		// 计算负载均衡率
		Double total_cpu = 0.0;
		Double total_ram = 0.0;
		Double total_bw = 0.0;
		for (String hostKey : hostmap.keySet()) {
			HostACO host = hostmap.get(hostKey);
			total_cpu += BigDecimalUtil.sub(host.getMips(),
					host.getCurrentMips());
			total_ram += BigDecimalUtil
					.sub(host.getRam(), host.getCurrentRam());
			total_bw += BigDecimalUtil.sub(host.getBw(), host.getCurrentBw());
		}
		Double avg_cpu = BigDecimalUtil.div(total_cpu, hostmap.size(), 4);
		Double avg_ram = BigDecimalUtil.div(total_ram, hostmap.size(), 4);
		Double avg_bw = BigDecimalUtil.div(total_bw, hostmap.size(), 4);
		Double balance_assist = 0.0;
		for (String hostKey : hostmap.keySet()) {
			HostACO host = hostmap.get(hostKey);
			balance_assist += Math.abs(BigDecimalUtil.sub(
					BigDecimalUtil.sub(host.getMips(), host.getCurrentMips()),
					avg_ram));
			balance_assist += Math.abs(BigDecimalUtil.sub(
					BigDecimalUtil.sub(host.getRam(), host.getCurrentRam()),
					avg_cpu));
			balance_assist += Math.abs(BigDecimalUtil.sub(
					BigDecimalUtil.sub(host.getBw(), host.getCurrentBw()),
					avg_bw));
		}
		balance_solution = BigDecimalUtil.div(3 * hostmap.size(),
				balance_assist, 4);
		solution = BigDecimalUtil.mul(0.4, sla_solution)
				+ BigDecimalUtil.mul(0.3, power_solution)
				+ BigDecimalUtil.mul(0.3, balance_solution);
		// 局部信息素更新
		for (String pro : programme) {
			Double update_p = BigDecimalUtil.mul(BigDecimalUtil.sub(1, rou),
					Pheromone.getPheromone(pro)) + solution;
			Pheromone.updatePheromone(pro, update_p);
			pheromoneSituation.remove(pro);
			pheromoneSituation.put(pro, 1);
		}
		for (String i : pheromoneSituation.keySet()) {
			if (pheromoneSituation.get(i) == 0) {
				Double update_p = BigDecimalUtil.mul(
						BigDecimalUtil.sub(1, rou), Pheromone.getPheromone(i));
				Pheromone.updatePheromone(i, update_p);
			}
		}
		pheromoneSituation = pS;
	}

	// 判断虚拟机vm能否放置到物理机host上
	private boolean canLoad(VMACO vm, HostACO host) {

		if (vm.getMips() > host.getCurrentMips()) {
			return false;
		}
		if (vm.getRam() > host.getCurrentRam()) {
			return false;
		}
		if (vm.getBw() > host.getCurrentBw()) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("all")
	public Map.Entry[] getSortedHashtableByValue(Map map) {
		Set set = map.entrySet();
		Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set
				.size()]);
		Arrays.sort(entries, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Double key1 = Double.valueOf(((Map.Entry) arg0).getValue()
						.toString());
				Double key2 = Double.valueOf(((Map.Entry) arg1).getValue()
						.toString());
				return key2.compareTo(key1);
			}
		});
		return entries;
	}

	@SuppressWarnings("all")
	public Map.Entry[] getSortedHashtableByValueUP(Map map) {
		Set set = map.entrySet();
		Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set
				.size()]);
		Arrays.sort(entries, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Double key1 = Double.valueOf(((Map.Entry) arg0).getValue()
						.toString());
				Double key2 = Double.valueOf(((Map.Entry) arg1).getValue()
						.toString());
				return key1.compareTo(key2);
			}
		});
		return entries;
	}
	
	public List<VMACO> getVmlist() {
		return vmlist;
	}

	public void setVmlist(List<VMACO> vmlist) {
		this.vmlist = vmlist;
	}

	public List<String> getProgramme() {
		return programme;
	}

	public Double getSolution() {
		return solution;
	}

	public Map<String, HostACO> getHostmap() {
		return hostmap;
	}

	public void setHostmap(Map<String, HostACO> hostmap) {
		this.hostmap = hostmap;
	}

	public void setSolution(Double solution) {
		this.solution = solution;
	}

	public Map<String, Integer> getPheromoneSituation() {
		return pheromoneSituation;
	}

	public void setPheromoneSituation(Map<String, Integer> pheromoneSituation) {
		this.pheromoneSituation = pheromoneSituation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<String, Integer> getpS() {
		return pS;
	}

	public void setpS(Map<String, Integer> pS) {
		this.pS = pS;
	}

}
