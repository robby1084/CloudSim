package org.iti.exam.wangyi;

import java.util.HashMap;
import java.util.Map;

public class StringKeyHashMapTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		Map<String,PeopleMsg> map = new HashMap<String, PeopleMsg>();
		
		PeopleMsg pm_1 = new PeopleMsg();
		pm_1.setCount("1");
		pm_1.setProvince("福建市");
		
		PeopleMsg pm_2 = new PeopleMsg();
		pm_2.setCount("2");
		pm_2.setProvince("河北省");
		
		PeopleMsg pm_3 = new PeopleMsg();
		pm_3.setCount("3");
		pm_3.setProvince("天津市");
		
		PeopleMsg pm_4 = new PeopleMsg();
		pm_4.setCount("4");
		pm_4.setProvince("河北省");
		
		String str = "河北省";
		System.out.println(str.hashCode());
		
		map.put("河北省", pm_2);
		map.put("河北省", pm_4);
		map.put("福建省", pm_1);
		map.put("天津市", pm_3);
		
		for(String s : map.keySet()){
			System.out.println(map.get(s).toString());
		}
		
		String s = "河北省";
		System.out.println(s.hashCode());
	}
	
}
