package org.iti.exam.wangyi;

import java.util.Scanner;

public class Summ {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String s = in.nextLine();
			String[] str = s.split(",");
			int[] data = new int[str.length];
			for(int i=0;i<str.length;i++){
				data[i] = Integer.parseInt(str[i]);
			}
			qsort_asc(data,0,data.length-1);
			System.out.println(data[data.length-1]);

		}
		
	}

	public static void qsort_asc(int source[], int low, int high){
		int i,j,x;
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
}
