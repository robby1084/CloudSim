package org.iti.sorted;

public class QuickSorted {

	public static void main(String[] args) {
		int[] a = {4,2,1,6,3,6,0,-5,1,1};
		int i;
		qsort_asc(a,0,a.length-1);
		for(i=0; i<10; i++){
			System.out.print(a[i]+",");
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
