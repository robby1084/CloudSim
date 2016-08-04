package org.iti.exam;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Heidong {

	public static void main(String[] args) {
		
		
		/*Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()){
			Integer num = sc.nextInt();
			while (num != 6174) {
				num = proceed(num);
			}
		}*/
		int i = 0xFFFFFFF1;
		int j = ~i;
		System.out.println(j);
		//int num = 38;
		//System.out.println("产生一个目标数："+num);
	}
	
	public static boolean judge(Integer num){
		
		convertToIntegerArray(num);
		return false;
	}
	
	public static Integer proceed(Integer num){
		Integer[] nums = convertToIntegerArray(num);
		Integer[] asc_nums = sort_asc(nums);
		Integer[] dec_nums = sort_dec(nums);
		Integer asc_i = convertToInteger(asc_nums);
		Integer dec_i = convertToInteger(dec_nums);
		Integer is6174 = null;
		if(asc_i>dec_i){
			is6174 = asc_i-dec_i;
			System.out.println(asc_i+"-"+dec_i+"="+(asc_i-dec_i));
		}else{
			is6174 = dec_i-asc_i;
			System.out.println(dec_i+"-"+asc_i+"="+(dec_i-asc_i));
		}
		return is6174;
	}
	
	public static Integer productNum(){
		Random random = new Random();
		Integer obj = random.nextInt(10000);
		if(obj < 1000){
			obj = productNum();
		}
		return obj;
	}
	
	public static Integer[] convertToIntegerArray(Integer num){

		String snum = num.toString();
		Integer[] obj = new Integer[4] ;
		if (snum.length() < 4) {
			int n = 4-snum.length();
			for(int i=0;i<n;i++){
				obj[i] = 0;
			}
			for(int i=0;i<4-n;i++){
				obj[i+n] = Integer.valueOf(snum.substring(i, i+1));
			}
			return obj;
		}
		
		for(int i=0;i<4;i++){
			obj[i] = Integer.valueOf(snum.substring(i, i+1));
		}
		return obj;
	}
	
	public static Integer convertToInteger(Integer[] nums){
		StringBuilder sb = new StringBuilder();
		for(Integer i : nums){
			sb.append(i);
		}
		return Integer.valueOf(sb.toString());
	}
	
	public static Integer[] sort_asc(Integer[] nums){
		Arrays.sort(nums);
		return nums;
	} 
	
	public static Integer[] sort_dec(Integer[] nums){
		Arrays.sort(nums);
		Integer[] ints = new Integer[4];
		for(int i=0;i<4;i++){
			ints[i] = nums[3-i];
		}
		return ints;
	}
}
