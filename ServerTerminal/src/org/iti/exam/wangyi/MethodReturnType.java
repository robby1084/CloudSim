package org.iti.exam.wangyi;

import java.util.Arrays;
import java.util.List;

public class MethodReturnType{

	public static void main(String[] args) {

		//System.out.println(Arrays.asList(new String[]{"1","2"}).remove(0));
		boolean b = decision();
		System.out.println(b);
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
