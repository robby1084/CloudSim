package org.iti.proxy.dynamicproxy.serviceimpl;

import org.iti.proxy.dynamicproxy.service.BookFacade;

public class BookFacadeImpl implements BookFacade{

	@Override  
    public void addBook() {  
        System.out.println("增加图书方法。。。");  
    }
}
