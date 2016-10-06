package org.iti.exam.wangyi;

import java.util.Scanner;

public class SohuTwo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int i = 0;
		while (scanner.hasNextLine()) {
			i++;
			System.out.print(i);
		}

	}

	// 得到学者能够拿到的最多的宝石数量
	public static int getNum(String str) {
		// 判断五种宝石是否都存在
		if (str.contains("A") && str.contains("B") && str.contains("C")
				&& str.contains("D") && str.contains("E")) {
			int sum = 0, max = 0;

			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) != 'A' && str.charAt(i) != 'B'
						&& str.charAt(i) != 'C' && str.charAt(i) != 'D'
						&& str.charAt(i) != 'E') {
					sum++;
				} else {
					if (max < sum) {
						max = sum;
					}
					sum = 0;
				}

			}
			// 当第一个不是五种宝石之一时，需要字符串前后不是五种宝石的数量相加
			if (str.charAt(0) != 'A' && str.charAt(0) != 'B'
					&& str.charAt(0) != 'C' && str.charAt(0) != 'D'
					&& str.charAt(0) != 'E') {
				sum = 0;
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) != 'A' && str.charAt(i) != 'B'
							&& str.charAt(i) != 'C' && str.charAt(i) != 'D'
							&& str.charAt(i) != 'E') {
						sum++;
					} else {
						break;
					}

				}
				for (int i = str.length() - 1; i >= 0; i--) {
					if (str.charAt(i) != 'A' && str.charAt(i) != 'B'
							&& str.charAt(i) != 'C' && str.charAt(i) != 'D'
							&& str.charAt(i) != 'E') {
						sum++;
					} else {
						break;
					}

				}
				if (max < sum) {
					max = sum;
				}
			}
			return max;
		} else {
			return 0;
		}

	}

}
