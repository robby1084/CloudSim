package org.iti.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.iti.rmi.service.HelloService;

public class RMIClientJNDI {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		String url = "rmi://localhost:2099/org.iti.rmi.server.HelloServiceImpl";
		HelloService helloService = (HelloService) Naming.lookup(url);
		String result = helloService.sayHello("Chen Junda");
		System.out.println(result);
	}
}
