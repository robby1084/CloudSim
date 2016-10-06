package org.iti.exam.wangyi;

import java.util.Arrays;
import java.util.List;

public class MethodReturnType{

	public static void main(String[] args) {

		//System.out.println(Arrays.asList(new String[]{"1","2"}).remove(0));
		//boolean b = decision();
		//System.out.println(b);
		Integer i1=127,i2=127,i3=128,i4=128;
		System.out.println(i1==i2);
		System.out.println(i1.equals(i2));
		System.out.println(i3==i4);
		System.out.println(i3.equals(i4));
	}
	
	public static <T> short method(byte x, double y){
		return (short)(x/y*2);
	}
	
	public static boolean decision(){
		boolean b = true;
		try {
			//b = false;
			return b;
		} finally{
			//b = false;
			return false;
		}
	}
}
