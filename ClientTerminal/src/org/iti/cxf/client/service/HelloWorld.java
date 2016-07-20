package org.iti.cxf.client.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.iti.cxf.client.dto.Customer;

@WebService
public interface HelloWorld {

	@WebMethod
    @WebResult String sayHi(@WebParam String text);
	
	@WebMethod
    @WebResult Customer findCustomer(@WebParam String id);
}
