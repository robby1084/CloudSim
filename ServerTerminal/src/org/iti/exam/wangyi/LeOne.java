package org.iti.exam.wangyi;

import java.util.Scanner;

public class LeOne {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String source = in.nextLine();
			String answer = in.nextLine();
			if(source == null || answer == null){
				System.out.println("NO");
			}
			char[] x = source.toCharArray();
			// 快排
			qsort_asc(x,0,x.length-1);
			// 判断前x个数字是否为0
			int count = 0;
			for(int i=0;i<source.length();i++){
				if (x[i] == '0') {
					count++;
				}else{
					break;
				}
			}
			// 将第一个0和第一个非0 的数字进行替换
			if(count>0){
				swap(x,count);
			}
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<source.length();i++){
				sb.append(x[i]);
			}
			if (sb.toString().equals(answer)) {
				System.out.println("YES");
			}else{
				System.out.println("NO");
			}
			
		}
		
	}
	
	public static void qsort_asc(char source[], int low, int high){
		int i,j;
		char x;
		if(low < high){
			i = low;
			j = high;
			x = source[i];
			while(i < j){
				while(i < j && source[j] > x){
					j--;
				}
				if(i < j){
					source[i++] = source[j];
				}
				while(i < j && source[i] < x){
					i++;
				}
				if (i < j) {
					source[j--] = source[i];
				}
				
			}
			source[i] = x;
			qsort_asc(source,low,i-1);
			qsort_asc(source,i+1,high);
		}
	}
	
	public static void swap(char[] c,int count){
		char temp = c[count];
		c[count] = c[0];
		c[0] = temp;
	}
}
