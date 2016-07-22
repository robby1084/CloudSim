package org.cloudbus.cloudsim.experiment.cjdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MHACOSimulation {

	private static final Double rou = 0.95;
	private static final Integer nAnts = 31;
	private static final Integer Imax = 200;

	private static final Integer numOfHost = 100;
	private static final Integer numofVM = 240;

	public static Map<String, Integer> pheromoneSituation = new HashMap<String, Integer>();

	public static void main(String[] args) {

		List<VMACO> vmlist = VMACOProvider.creatVM(numofVM);
		List<HostACO> hostlist = HostAVOProvider.creatHost(numOfHost);
		Map<String, HostACO> hostmap = new HashMap<String, HostACO>();
		for (HostACO host : hostlist) {
			hostmap.put(host.getHostname(), host);
		}
		Map<String, Double> pheromoneMap = initPheromone(vmlist, hostlist);
		Pheromone.initPheromone(pheromoneMap);
		initPheromoneSituation(vmlist, hostlist);
		// 创建蚂蚁
		List<Ant> antlist = new ArrayList<Ant>();
		for (int i = 1; i <= nAnts; i++) {
			Ant ant = new Ant(i, vmlist, hostmap, pheromoneSituation);
			antlist.add(ant);
		}

		// 算法开始
		System.out.println("==========simulation start==========");
		
		// check Pheromone
		/*for(String s : Pheromone.pheromone.keySet()){
			System.out.println(s);
		}*/
		
		Calendar startTime = Calendar.getInstance();
		System.out.println("CurrentTime:" + startTime.getTime().getTime());

		Ant bestAnt = null ;
		Map<Double, Ant> rankForBestSolution = new HashMap<Double, Ant>();
		for (int i = 1; i <= Imax; i++) {
			for (Ant ant : antlist) {
				ant.antErgodic();
				rankForBestSolution.put(ant.getSolution(), ant);
			}
			Double pheromone_max = 0.0;
			Map<Double, Ant> rankDownMap = sortMapByKey(rankForBestSolution);
			for (Double d : rankDownMap.keySet()) {
				pheromone_max = BigDecimalUtil.div(BigDecimalUtil.mul(d, 7),
						rou, 4);
				if (i == Imax) {
					bestAnt = rankDownMap.get(d);
				}
			}
			// 局部信息素更新
			int flag = 1;
			int mapSize = rankDownMap.size();
			Double pheromone_min = BigDecimalUtil.div(pheromone_max,
					2 * rankForBestSolution.size(), 4);
			for (Double solution : rankDownMap.keySet()) {
				if (flag > 7 || flag >= mapSize) {
					break;
				}
				Ant ant = rankDownMap.get(solution);
				// 某一条路径更新
				for (String pro : ant.getProgramme()) {
					// 如果信息素已经修改过了，则跳过更新下一个
					if (pheromoneSituation.get(pro) == 1) {
						continue;
					}
					Double update_p = BigDecimalUtil.mul(
							BigDecimalUtil.sub(1, rou),
							Pheromone.getPheromone(pro))
							+ (8 - flag) * solution;
					if (update_p >= pheromone_max) {
						Pheromone.updatePheromone(pro, pheromone_max);
					}else if(update_p <= pheromone_min){
						Pheromone.updatePheromone(pro, pheromone_min);
					}else{
						Pheromone.updatePheromone(pro, update_p);
					}
					pheromoneSituation.remove(pro);
					pheromoneSituation.put(pro, 1);
				}
				flag++;
			}
			for (String k : pheromoneSituation.keySet()) {
				if (pheromoneSituation.get(k) == 0) {
					Double update_p = BigDecimalUtil.mul(
							BigDecimalUtil.sub(1, rou),
							Pheromone.getPheromone(k));
					if (update_p >= pheromone_max) {
						Pheromone.updatePheromone(k, pheromone_max);
					}else if(update_p <= pheromone_min){
						Pheromone.updatePheromone(k, pheromone_min);
					}else{
						Pheromone.updatePheromone(k, update_p);
					}
				}
			}
			pheromoneSituation.clear();
			initPheromoneSituation(vmlist, hostlist);
		}

		if (bestAnt != null) {
			System.out.println("部署方案为：");
			for(String program : bestAnt.getProgramme()){
				System.out.println(program);
			}
			System.out.println("最佳SLA履约率为："+bestAnt.getSolution());
			System.out.println("最佳能源消耗工作量为："+bestAnt.getSolution());
			System.out.println("最佳负载均衡率为："+bestAnt.getSolution());
			System.out.println("最佳目标函数为："+bestAnt.getSolution());
		}
		// 算法结束
		Calendar endTime = Calendar.getInstance();
		System.out.println("CurrentTime:" + endTime.getTime().getTime());
		System.out.println("MHACO Algorithn running time:"
				+ String.valueOf(endTime.getTime().getTime()
						- startTime.getTime().getTime()));
		System.out.println("==========simulation start==========");
	}

	public static Map<String, Double> initPheromone(List<VMACO> vmlist,
			List<HostACO> hostlist) {

		Map<String, Double> p = new HashMap<String, Double>();
		for (VMACO vm : vmlist) {
			for (HostACO host : hostlist) {
				String key = vm.getVmm() + "," + host.getHostname();
				p.put(key, 10.0);
			}
		}
		return p;
	}

	public static void initPheromoneSituation(List<VMACO> vmlist,
			List<HostACO> hostlist) {

		for (VMACO vm : vmlist) {
			for (HostACO host : hostlist) {
				String key = vm.getVmm() + "," + host.getHostname();
				pheromoneSituation.put(key, 0);
			}
		}
	}

	// Map按key降序排序
	public static Map<Double, Ant> sortMapByKey(Map<Double, Ant> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<Double, Ant> sortMap = new TreeMap<Double, Ant>(
				new Comparator<Double>() {
					@Override
					public int compare(Double arg0, Double arg1) {
						return arg1.compareTo(arg0);
					}
				});

		sortMap.putAll(map);
		return sortMap;
	}

	@SuppressWarnings("all")
	public static Map.Entry[] getSortedHashtableByValue(Map map) {
		Set set = map.entrySet();
		Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set
				.size()]);
		Arrays.sort(entries, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Long key1 = Long.valueOf(((Map.Entry) arg0).getValue()
						.toString());
				Long key2 = Long.valueOf(((Map.Entry) arg1).getValue()
						.toString());
				return key2.compareTo(key1);
			}
		});
		return entries;
	}
}
