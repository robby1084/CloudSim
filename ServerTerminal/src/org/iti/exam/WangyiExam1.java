package org.iti.exam;

import java.util.Scanner;

public class WangyiExam1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()){
			String cocks = sc.nextLine();
			String[] kindsOfCocks = cocks.split(" ");
			System.out.println(kindsOfCocks.length);
		}
	}
	
	
}
