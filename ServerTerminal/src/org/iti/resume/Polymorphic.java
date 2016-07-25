package org.iti.resume;

public class Polymorphic {

	public static void main(String[] args) {
		/*Father a = new Son();
		System.out.println(a.b);
		a.getB();*/
		go(new Son());
	}
	
	static void go(Son son){
		son.add(8);
	}
}
