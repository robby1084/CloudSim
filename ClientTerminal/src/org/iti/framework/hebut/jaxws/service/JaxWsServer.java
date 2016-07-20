package org.iti.framework.hebut.jaxws.service;

import java.io.Serializable;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name="JaxWebService")
public interface JaxWsServer extends Serializable{

	@WebMethod
	public String loadUserNameByCode(String code);
}
