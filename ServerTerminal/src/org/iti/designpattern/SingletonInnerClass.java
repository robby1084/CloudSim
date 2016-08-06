package org.iti.designpattern;

public class SingletonInnerClass {

	public static class Inner {
		static {
			System.out.println("TestInner Static!");
		}
		public final static SingletonInnerClass testInstance = new SingletonInnerClass(3);
	}

	public static SingletonInnerClass getInstance() {
		return Inner.testInstance;
	}

	public SingletonInnerClass(int i) {
		System.out.println("Test " + i + " Construct! ");
	}

	// 类静态块
	static {
		System.out.println("Test Static");
	}

	// 类静态属性
	public static SingletonInnerClass testOut = new SingletonInnerClass(1);

	public static void main(String args[]) {
		SingletonInnerClass t = new SingletonInnerClass(2);
		SingletonInnerClass.getInstance();
	}

}
