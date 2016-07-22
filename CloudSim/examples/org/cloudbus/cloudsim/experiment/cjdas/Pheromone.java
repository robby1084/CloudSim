package org.cloudbus.cloudsim.experiment.cjdas;

import java.util.HashMap;
import java.util.Map;

public class Pheromone {

	/**
	 * 存放信息素信息
	 * key:vm name,host name
	 * value:pheromone
	 */
	public static final Map<String, Double> pheromone = new HashMap<String, Double>();

	public static void initPheromone(Map<String, Double> ps) {

		pheromone.putAll(ps);
	}

	/**
	 * 获得信息素
	 * @param key
	 */
	public static Double getPheromone(String key){
		if (!pheromone.containsKey(key)) {
			return null;
		}
		return pheromone.get(key);
	}
	
	/**
	 * 更新信息素
	 * 
	 * @return 成功与否
	 */
	public static boolean updatePheromone(String combinationName, Double value) {

		if (combinationName == null || "".equals(combinationName)) {
			return false;
		}
		if (pheromone.containsKey(combinationName)) {
			pheromone.remove(combinationName);
			pheromone.put(combinationName, value);
			return true;
		}
		return false;
	}

	public static Map<String, Double> getPheromone() {
		return pheromone;
	}

}
