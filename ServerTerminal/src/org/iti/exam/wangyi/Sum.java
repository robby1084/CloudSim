package org.iti.exam.wangyi;

import java.util.Scanner;

public class Sum {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {// 注意while处理多个case
			String mToN = sc.nextLine();
			String first = sc.nextLine();
			String second = sc.nextLine();
			containString(mToN, first);
			containString(mToN, second);
		}
	}

	public static void containString(String parent, String son) {

		boolean f=false,b=false;
		if (parent.indexOf(son) != -1) {
			f=true;
		}
		String p = reverse(parent);
		if (p.indexOf(son) != -1) {
			b=true;
		}
		if(f && b){
			System.out.println("both");
		}else if(!f && b){
			System.out.println("backward");
		}else {
			System.out.println("forward");
		}

	}

	public static String reverse(String s) {
		int length = s.length();
		if (length <= 1)
			return s;
		String left = s.substring(0, length / 2);
		String right = s.substring(length / 2, length);
		return reverse(right) + reverse(left);
	}
}
