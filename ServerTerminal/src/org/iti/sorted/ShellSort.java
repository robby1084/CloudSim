package org.iti.sorted;

public class ShellSort {

	public static void main(String[] args) {

		int a[] = { 1, 54, 6, 3, 78, 34, 12, 45, 56, 100 };
		shellSort(a);
		for(int i : a){
			System.out.print(i+",");
		}
	}

	public static void shellSort(int[] source) {

		double d1 = source.length;
		int d;
		int temp = 0;
		do {
			//d1 = Math.ceil(d1 / 2);
			d1 = d1/3+1;
			d = (int) d1;
			for (int x = 0; x < d; x++) {
				for (int i = x + d; i < source.length; i += d) {
					int j = i - d;
					temp = source[i];
					for (; j >= 0 && temp < source[j]; j -= d) {
						source[j + d] = source[j];
					}
					source[j + d] = temp;
				}
			}
			
		}while(d>1);
	}
}
