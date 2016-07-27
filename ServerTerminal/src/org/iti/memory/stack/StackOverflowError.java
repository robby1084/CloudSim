package org.iti.memory.stack;

public class StackOverflowError {

	private int stackLength = 1;
	
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}
	
	public static void main(String[] args) throws Throwable {
		StackOverflowError oom = new StackOverflowError();
		try {
			oom.stackLeak();
		} catch (Throwable e) {
			System.out.println("stack length:"+oom.stackLength);
			throw e;
		}
	}
}
