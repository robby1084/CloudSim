package org.iti.cas;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

	private final static AtomicReference<String> atomicReference = new AtomicReference<String>("abc");
	
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			final int num = i;
			new Thread(){
				public void run(){
					try {
						Thread.sleep((int) Math.abs(Math.random()*100));
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (atomicReference.compareAndSet("abc", new String("abc"))) {
						System.out.println("我是线程："+num+"，我获得了锁进行了对象修改！");
					}
				}
			}.start();
				
			
		}
	}
}
