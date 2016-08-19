package org.iti.exam.wangyi;

public class MyClassLoader extends ClassLoader{

	// 类加载器的名称
	private String name;
	// 加载类的路径
	private String path = "D:/";
	private final String fileType = ".class";
	
	public MyClassLoader(String name){
		//让系统类加载器成为该类加载器的父加载器
		super();
		this.name = name;
	}
	
	public MyClassLoader(ClassLoader parent, String name){
		// 显示制定该类加载器的父加载器
		super(parent);
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}
