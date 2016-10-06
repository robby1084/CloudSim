package org.iti.exam.wangyi;

import java.util.Scanner;

public class LeTwo {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			Double inputNum = in.nextDouble();
			double sum = 0;
			double outputNum = 0;
			if (inputNum != 0) {
				for(double i=1;;i++){
					if (sum+i<inputNum) {
						sum = sum+i;
					}else if (sum+i > inputNum) {
						sum = sum-i;
					}else if (sum+i == inputNum){
						outputNum = i;
						break;
					}
				}
			}
			System.out.println(outputNum);
		}
		//int inputNum = 6;
		
	}
}
