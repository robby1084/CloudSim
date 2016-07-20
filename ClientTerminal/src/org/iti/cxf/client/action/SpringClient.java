package org.iti.cxf.client.action;

import org.iti.cxf.client.service.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringClient {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"JaxWsClient.xml");
		HelloWorld helloworld = (HelloWorld) context
				.getBean("cxfClient");
		System.out.println(helloworld.sayHi("chenjunda"));
	}
}
