package org.cloudbus.cloudsim.experiment.cjdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Set;

public class Test {

	public static void main(String[] args) {

		// System.out.println(Math.floor(Math.random()*3));
		// System.out.println(Math.random()*3);
		String a = "a";
		String b = "b";
		Test ts = new Test();
		ts.change(a, b);
		System.out.println(a + b);
		System.out.println("--------");
		System.out.println(ts.change(b));
		

		/*Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("aa", 22);
		map.put("bb", 11);
		map.put("cc", 44);
		map.put("dd", 33);
		map.put("ee", 55);
		Map.Entry[] entries = getSortedHashtableByValue(map);
		for (int i = 0; i < entries.length; i++) {
			System.out.println(entries[i].getValue());
		}*/
		
		/*List<VMACO> list = new ArrayList<VMACO>();
		list.add(new VMACO(1, 2, 2, 2, 2, 2, "1"));
		list.add(new VMACO(2, 2, 2, 2, 2, 2, "2"));
		list.add(new VMACO(3, 2, 2, 2, 2, 2, "3"));
		Iterator<VMACO> ite = list.iterator();
		while(ite.hasNext()){
			VMACO i = ite.next();
			if(i.getId()==2){
				ite.remove();
			}
		}
		for(VMACO i : list){
			System.out.println(i.getVmm());
		}*/
		//System.out.println(BigDecimalUtil.div(1, 3, 4));
		/*for (int i = 1; i <= 30; i++) {
			System.out.println(new Random().nextInt(3));
		}*/
		/*Map<Double,String> map = new HashMap<Double,String>();
		map.put(3.0, "3.0");
		map.put(1.0, "1.0");
		map.put(2.0, "2.0");
		map.put(6.0, "6.0");
		for(Double d : map.keySet()){
			System.out.println(d);
		}
		System.out.println("=========");
		map = sortMapByKey(map);
		for(Double d : map.keySet()){
			System.out.println(d);
		}*/
		/*String a = "a";
		String b = "b";
		String c = a+b;
		String d = "ab";
		String e = "ab";
		System.out.println(c==d);
		System.out.println(d==e);*/
	}

	public static Map<Double, String> sortMapByKey(Map<Double, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<Double, String> sortMap = new TreeMap<Double, String>(
                new Comparator<Double>() {
					@Override
					public int compare(Double arg0, Double arg1) {
						return arg1.compareTo(arg0);
					}
                });

		sortMap.putAll(map);
		return sortMap;
	}
	
	public static Map.Entry[] getSortedHashtableByValue(Map map) {   
	       Set set = map.entrySet();   
	       Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set.size()]);   
	       Arrays.sort(entries, new Comparator() {   
	           public int compare(Object arg0, Object arg1) {   
	        	   Double key1 = Double.valueOf(((Map.Entry) arg0).getValue().toString());   
	        	   Double key2 = Double.valueOf(((Map.Entry) arg1).getValue().toString());   
	               return key2.compareTo(key1);   
	           }   
	       });
	       Map result = new HashMap();
	       
	       return entries;   
	} 
	
	public static Map sortMap(Map oldMap) {
		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				oldMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<java.lang.String, Integer> arg0,
					Entry<java.lang.String, Integer> arg1) {
				return arg0.getValue() - arg1.getValue();
			}
		});
		Map newMap = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			newMap.put(list.get(i).getKey(), list.get(i).getValue());
		}
		return newMap;
	}

	private static void printMap(Map map) {
		System.out.println("===================mapStart==================");
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		System.out.println("===================mapEnd==================");
	}

	public void change(String _a, String _b) {
		_a = "aa";
		_b = "bb";
	}

	public String change(String _b) {
		_b = "bbb";
		return _b;
	}
}
