package org.iti.exam.wangyi;

public class MultiThreadSync {

	public synchronized static void m1() throws InterruptedException {
		System.out.println("This is m1");
		Thread.sleep(100);
		System.out.println("m1 done");
	}

	public synchronized static void m2() throws InterruptedException {
		System.out.println("This is m2");
		Thread.sleep(100);
		System.out.println("m2 done");
	}

	public synchronized void m3() throws InterruptedException {
		System.out.println("This is m3");
		Thread.sleep(100);
		System.out.println("m3 done");
	}

	public synchronized void m4() throws InterruptedException {
		System.out.println("This is m4");
		Thread.sleep(100);
		System.out.println("m4 done");
	}

	public static void start() {
		final MultiThreadSync a1 = new MultiThreadSync();
		final MultiThreadSync a2 = new MultiThreadSync();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					MultiThreadSync.m1();
					a1.m3();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "T1").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					MultiThreadSync.m1();
					MultiThreadSync.m2();
					a1.m3();
					a1.m4();
					a2.m3();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "T2").start();
	}
	
	public static void main(String[] args) {
		start();
	}
}
