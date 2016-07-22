package org.cloudbus.cloudsim.experiment.cjdas;

public class StringBufferOperateTest {

	public static void main(String[] args) {
		StringBuffer a = new StringBuffer("A");
		StringBuffer b = new StringBuffer("B");
		operate(a, b);
		System.out.println(a+","+b);
		System.out.println("-----------");
		String str = "abcd";
		String s1 = str.substring(0, 2);
		String s2 = str.substring(0, 2);
		System.out.println("s1 = "+s1);
		System.out.println("s2 = "+s2);
		System.out.println(s1==s2);
		
		
		
	}
	
	static void operate(StringBuffer x,StringBuffer y){
		x.append(y);
		y = x;
	}
}
