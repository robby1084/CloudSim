package org.iti.exam.wangyi;

import java.util.Scanner;

public class SohuThree {

	public static void main(String[] args) {
		/*Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String num = in.nextLine();
			int count = in.nextInt();
			char[] numbers = num.toCharArray();
			char[] before = numbers;
			char[] after = new char[numbers.length-count];
			char[] alter = new char[count];
			qsort_asc(numbers,0,numbers.length-1);
			if (count != 0) {
				for(int i=0;i<count;i++){
					alter[i] = numbers[i];
				}
			}
			for(int i =0;i<numbers.length;i++){
				for(int j=0;j<alter.length;j++){
					if (before[i] != alter[j]) {
						after[i-j] = before[i];
					}
				}
			}
			System.out.println(after.toString());
		}*/
		String num = "325674";
		int count = 2;
		char[] numbers = num.toCharArray();
		char[] before = num.toCharArray();
		char[] after = new char[numbers.length-count];
		char[] alter = new char[count];
		qsort_asc(numbers,0,numbers.length-1);
		if (count != 0) {
			for(int i=0;i<count;i++){
				alter[i] = numbers[i];
			}
		}
		int[] xs = new int[count];
		for(int j=0;j<alter.length;j++){
			int xiabiao = before.toString().indexOf(String.valueOf(alter[j]));
			//before.
			xs[j] = xiabiao;
		}
		for(int i =0;i<numbers.length;i++){
			/*for(int j=0;j<alter.length;j++){
				if (before[i] != alter[j]) {
					after[i-j] = before[i];
				}
			}*/
			
			
		}
		System.out.println(after.toString());
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
}
