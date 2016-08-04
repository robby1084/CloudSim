package org.iti.exam;

import java.util.Scanner;

public class WangyiExam2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()){
			int n = sc.nextInt();
			int m = sc.nextInt();
			// 将n行m列的土地价值放在数组中
			int[][] values = new int[m][n];
			for(int i=0;i<n;i++){
				String line = sc.nextLine();
				for(int j=0;j<m;j++){
					values[i][j] = Integer.valueOf(line.substring(j, j+1));
				}
			}
			// 这里处理二维数组，对其进行分割。
			int[][] result = new int[4][4];
			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					result[i][j] = values[i][j];
				}
			}
		}
	}
	
	// 计算二维数组中的最小值
	public static int countMinValue(int[][] arr){
		
		
		return 0;
	}
}
