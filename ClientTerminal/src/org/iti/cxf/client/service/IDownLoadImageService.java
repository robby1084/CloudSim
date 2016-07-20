package org.iti.cxf.client.service;

import java.io.Serializable;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.servlet.http.HttpServletResponse;

@WebService
public interface IDownLoadImageService extends Serializable {

	@WebMethod
	@WebResult
	String downLoadImage(@WebParam HttpServletResponse response,
			@WebParam String pictureName);
}
