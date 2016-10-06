package org.iti.exam.wangyi;

import java.util.Scanner;

public class SohuFive {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String n = in.nextLine();
			String str = in.nextLine();
			String[] numbers = str.split(" ");
			int[] di = new int[numbers.length];
			for (int i = 0; i < numbers.length; i++) {
				di[i] = Integer.parseInt(numbers[i]);
			}

			int num = getNum(di);
			System.out.println(num);

		}
	}

	// 得到需要题目数目
	public static int getNum(int[] degree) {
		// 冒泡排序,从小到大排序
		/*int temp = 0;
		for (int i = 0; i < degree.length; i++) {
			for (int j = i + 1; j < degree.length; j++) {
				if (degree[i] > degree[j]) {
					temp = degree[i];
					degree[i] = degree[j];
					degree[j] = temp;
				}
			}
		}*/
		qsort_asc(degree,0,degree.length-1);
		// 所需题目数量
		int num = 0;
		// 用于记录三道题合格数目
		int k = 1;
		for (int i = 1; i < degree.length; i++) {
			if (k == 3) {
				k = 1;
			} else {
				if (degree[i] - degree[i - 1] <= 10) {
					k++;
				} else {
					num = num + (3 - k);
					k = 1;
				}
			}

		}
		if (k < 3) {
			num = num + (3 - k);
		}
		return num;

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
