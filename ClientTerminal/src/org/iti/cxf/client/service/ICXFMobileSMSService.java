package org.iti.cxf.client.service;

import java.io.Serializable;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.iti.base.rmi.mobilesms.dto.BatchsubmitDTO;
import org.iti.base.rmi.mobilesms.dto.SingleSubmitListDTO;


@WebService
public interface ICXFMobileSMSService extends Serializable {

	@WebMethod
	@WebResult
	public String singleShotSMS(@WebParam String phone, @WebParam String content);

	@WebMethod
	@WebResult
	public String singleShotSMSCollection(
			@WebParam List<SingleSubmitListDTO> list);

	@WebMethod
	@WebResult
	public String batchSubmit(@WebParam BatchsubmitDTO batchsubmitDTO);

	
}
