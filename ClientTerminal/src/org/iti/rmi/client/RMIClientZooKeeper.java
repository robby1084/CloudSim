package org.iti.rmi.client;

import java.rmi.RemoteException;

import org.iti.rmi.service.HelloService;

public class RMIClientZooKeeper {

	public static void main(String[] args) throws RemoteException, InterruptedException {
		
		ServiceConsumer consumer = new ServiceConsumer();
		
		while (true) {
			
			HelloService helloService = consumer.lookup();
			String result = helloService.sayHello("Chen Junda");
			System.out.println(result);
			Thread.sleep(3000);
		}
	}
}
