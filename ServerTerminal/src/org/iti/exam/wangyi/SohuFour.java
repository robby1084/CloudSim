package org.iti.exam.wangyi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SohuFour {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String mAndN = in.nextLine();
			String[] mAN = mAndN.split(" ");
			int n = Integer.parseInt(mAN[0]);
			int m = Integer.parseInt(mAN[1]);
			String ns = in.nextLine();
			String[] numbers = ns.split(" ");
			long[] shu = new long[numbers.length];
			for(int i=0;i<numbers.length;i++){
				shu[i]=Long.parseLong(numbers[i]);
			}
			// 存放结果的List
			List<Long> result = new ArrayList<Long>();
			for(int i=0;i<numbers.length;i++){
				for(int j=0;j<numbers.length;j++){
					if(i==j){
						continue;
					}
					long n1 = shu[i];
					long n2 = shu[j];
					long s = n1^n2;
					result.add(s);
				}
			}
			long count = 0;
			for(Long intValue : result){
				if(intValue > m){
					count++;
				}
			}
			count = count/2;
			System.out.println(count);
			
		}
		
	}
}
