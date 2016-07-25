package org.iti.resume;

public class Son extends Father {

	private int a = 30;
	public int b = 40;

	Son(int i){
		super();
		System.out.println("I am Son");
	}
	
	Son(){
		add(2);
	}
	
	void add(int v){
		i+=v*2;
		System.out.println(i);
	}
	
	private void getA() {
		System.out.println("class B的a值：" + a);
	}

	public void getB() {
		System.out.println("class B的b值：" + b);
	}
}
