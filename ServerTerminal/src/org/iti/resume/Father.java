package org.iti.resume;

public class Father {

	private int a = 10;
	public int b = 20;
	int i;
	
	Father(){
		add(1);
		System.out.println(i);
	}

	Father(int i){
		System.out.println("I am Father");
	}
	
	void add(int v){
		i+=v;
		System.out.println(i);
	}
	
	private void getA() {
		System.out.println("class A的a值：" + a);
	}

	public void getB() {
		System.out.println("class A的b值：" + b);
	}
}
