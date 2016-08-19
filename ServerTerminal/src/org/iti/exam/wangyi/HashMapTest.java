package org.iti.exam.wangyi;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		Map<Key,PeopleMsg> map = new HashMap<Key, PeopleMsg>();
		
		PeopleMsg pm_1 = new PeopleMsg();
		pm_1.setCount("1");
		pm_1.setPhone("phone");
		pm_1.setProvince("福建省");
		pm_1.setSex("男");
		
		PeopleMsg pm_2 = new PeopleMsg();
		pm_2.setCount("2");
		pm_2.setProvince("河北省");
		
		PeopleMsg pm_3 = new PeopleMsg();
		pm_3.setCount("3");
		pm_3.setProvince("天津市");
		
		PeopleMsg pm_4 = new PeopleMsg();
		pm_4.setCount("4");
		pm_4.setProvince("河北省");
		
		System.out.println(pm_1.hashCode());
		System.out.println(pm_2.hashCode());
		System.out.println(pm_3.hashCode());
		System.out.println(pm_4.hashCode());
		
		map.put(new Key("河北省"), pm_2);
		map.put(new Key("河北省"), pm_4);
		map.put(new Key("福建省"), pm_1);
		map.put(new Key("天津市"), pm_3);
		
		for(Key s : map.keySet()){
			System.out.println(map.get(s).toString());
		}
		
		
	}
	
}
