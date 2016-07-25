package org.iti.proxy.dynamicproxy.serviceimpl;

import org.iti.proxy.dynamicproxy.service.BookFacade;

public class TestProxy {

	public static void main(String[] args) {  
        BookFacadeProxy proxy = new BookFacadeProxy();  
        BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());  
        bookProxy.addBook();  
    }
}
