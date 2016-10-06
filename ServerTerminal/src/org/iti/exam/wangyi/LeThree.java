package org.iti.exam.wangyi;

import java.util.Scanner;

public class LeThree {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			int total = in.nextInt();
			for(int i = 0;i < total;i++){
				String rowAndColumn = in.nextLine();
				String[] rAndC = rowAndColumn.split(" ");
				int row = Integer.parseInt(rAndC[0]);
				int column = Integer.parseInt(rAndC[1]);
				// 创建一个二维数组来存放矩阵
				char[][] jz = new char[row][column];
				// 输入矩阵
				for(int j=0;j<row;j++){
					String s = in.nextLine();
					jz[i] = s.toCharArray();
				}
				for(int j=1;j<row-1;j++){
					for(int k=1;k<column-1;k++){
						if (jz[j][k] == '1') {
							while(jz[j][k+1] == '1' || jz[j+1][k+1] == '1'){
								
							}
						}
					}
				}
				
			}
		}
	}
}
