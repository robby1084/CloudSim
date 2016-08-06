package org.iti.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {

	private static final AtomicStampedReference<String> atomicStampReference = new AtomicStampedReference<String>(
			"abc", 0);
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			final int num = i;
			final int stamp = atomicStampReference.getStamp();
			new Thread() {
				public void run() {
					try {
						Thread.sleep(Math.abs((int) (Math.random() * 100)));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (atomicStampReference.compareAndSet("abc", "abc2", stamp,
							stamp + 1)) {
						System.out.println("我是线程：" + num + ",我获得了锁进行了对象修改！");
					}
				}
			}.start();
		}
		new Thread() {
			public void run() {
				int stamp = atomicStampReference.getStamp();
				while (!atomicStampReference.compareAndSet("abc2", "abc", stamp,
						stamp + 1))
					;
				System.out.println("已经改回为原始值！");
			}
		}.start();
	}
}
