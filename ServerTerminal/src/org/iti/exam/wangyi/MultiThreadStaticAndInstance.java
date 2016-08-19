package org.iti.exam.wangyi;

public class MultiThreadStaticAndInstance {

	public static synchronized void m1() throws InterruptedException{
		System.out.println("This is m1");
		Thread.sleep(1000);
		System.out.println("m1 done");
	}
	
	public synchronized void m2() throws InterruptedException{
		System.out.println("This is m2");
		Thread.sleep(1000);
		System.out.println("m2 done");
	}
	
	public static void main(String[] args) {
		final MultiThreadStaticAndInstance a = new MultiThreadStaticAndInstance();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					MultiThreadStaticAndInstance.m1();
					a.m2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();;
	}
}
