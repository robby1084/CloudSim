package org.iti.proxy.dynamicproxy.serviceimpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BookFacadeProxy implements InvocationHandler{

	private Object target;
	
	 /** 
     * 绑定委托对象并返回一个代理类 
     * @param target 
     * @return 
     */
	public Object bind(Object target){
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	
	/** 
     * 调用方法 
     */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		Object result = null;
		System.out.println("事务开始");
		// 执行方法
		result = method.invoke(target, args);
		System.out.println("事物结束");
		return result;
	}

}
