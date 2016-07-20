package org.iti.framework.hebut.mainClass;

import org.iti.application.context.bean.factory.BeanFactory;
import org.iti.framework.hebut.jaxws.service.JaxWsServer;

public class JavaXmlWebService {

	public static void main(String[] args) {
		
		String name = loadJaxWsBean().loadUserNameByCode("102458");
		System.out.println(name);
	}
	
	public static JaxWsServer loadJaxWsBean(){
		return (JaxWsServer) BeanFactory.getBean("jawWsService");
	}
}
