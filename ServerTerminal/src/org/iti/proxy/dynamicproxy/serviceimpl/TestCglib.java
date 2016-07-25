package org.iti.proxy.dynamicproxy.serviceimpl;

public class TestCglib {

	public static void main(String[] args) {  
		BookFacadeCglibProxy cglib=new BookFacadeCglibProxy();  
		BookFacadeImplCglib bookCglib=(BookFacadeImplCglib)cglib.getInstance(new BookFacadeImplCglib());  
        bookCglib.addBook();  
    }
}
