package org.iti.exam.wangyi;

import java.util.Scanner;

public class SohuOne {

	public static void main(String[] args) {
		/*Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			int length = in.nextInt();
			String str = in.nextLine();
			String[] ss = str.split(" ");
			if (ss.length != length) {
				System.out.println(-1);
			}
			int[] steps = new int[length];
			for(int i=0;i<ss.length;i++){
				steps[i] = Integer.parseInt(ss[i]);
			}
			countNum(steps);
		}*/
		String str = "2 0 2 1 2";
		String[] ss = str.split(" ");
		if (ss.length != 5) {
			System.out.println(-1);
		}
		int[] steps = new int[5];
		for(int i=0;i<ss.length;i++){
			steps[i] = Integer.parseInt(ss[i]);
		}
		countNum(steps);
		
	}
	
	public static void countNum(int[] steps){
		
		int count = 0;
		int currentStep = 0;
		if(steps[0] == 0){
			System.out.println(-1);
		}
		while(currentStep <= steps.length-1){
			if (steps[currentStep] == 0) {
				System.out.println(-1);
				break;
			}
			int bushu = steps[currentStep];
			currentStep = currentStep+bushu;
			count++;
		}
		if (count != 0) {
			System.out.println(count);
		}else {
			System.out.println(-1);
		}
	}
}
