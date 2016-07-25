package org.iti.proxy.dynamicproxy.serviceimpl;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class BookFacadeCglibProxy implements MethodInterceptor{

	private Object target;
	
	/** 
     * 创建代理对象 
     *  
     * @param target 
     * @return 
     */
	public Object getInstance(Object target){
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.getClass());
		// 回调方法
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	@Override
	public Object intercept(Object obj, Method arg1, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("事物开始");  
        proxy.invokeSuper(obj, args);  
        System.out.println("事物结束");  
        return null;
	}

	
}
