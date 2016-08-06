package org.iti.cas;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicMarkableReferenceTest {

	private static final AtomicMarkableReference<String> atomicMarkableReference = new AtomicMarkableReference<String>(
			"abc", false);

	public static void main(String[] args) {

		for (int i = 0; i < 100; i++) {
			final int num = i;
			new Thread() {
				public void run() {
					try {
						Thread.sleep(Math.abs((int) (Math.random() * 100)));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (atomicMarkableReference.compareAndSet("abc", "abc2", false,
							true)) {
						System.out.println("我是线程：" + num + ",我获得了锁进行了对象修改！");
					}
				}
			}.start();
		}
		new Thread() {
			public void run() {
				while (!atomicMarkableReference.compareAndSet("abc2", "abc", false,
						true))
					;
				System.out.println("已经改回为原始值！");
			}
		}.start();
	
	}
}
