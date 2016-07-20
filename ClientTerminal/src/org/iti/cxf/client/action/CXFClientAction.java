package org.iti.cxf.client.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.iti.application.context.bean.factory.BeanFactory;
import org.iti.base.rmi.mobilesms.dto.BatchsubmitDTO;
import org.iti.base.rmi.mobilesms.dto.SingleSubmitListDTO;
import org.iti.common.util.JsonUtil;
import org.iti.cxf.client.service.ICXFMobileSMSService;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("CXFClientAction")
@Scope("prototype")
public class CXFClientAction extends AbstractHttpInterfaceAction{

	/**
	 * CXF客户端测试
	 */
	private static final long serialVersionUID = -5136248547420581751L;

	private String phone = "18892273099";
	private String mashi = "15522626092";
	private String liufengsheng = "18020033162";
	private String singleShotContent = "WebService短信单发";
	private String singleShotListContent = "cxf单发多条短信测试";
	private String batchShotContent = "cxf群发短信测试";
	
	@Override
	public String defaultExecute() throws Throwable {

		String state_01 = getICXFMobileSMSService().singleShotSMS(phone,
				URLEncoder.encode(singleShotContent, "utf-8"));
		List<SingleSubmitListDTO> list = new ArrayList<SingleSubmitListDTO>();
		SingleSubmitListDTO sDTO_1 = new SingleSubmitListDTO();
		sDTO_1.setPhone(phone);
		sDTO_1.setContent(URLEncoder.encode(singleShotListContent, "utf-8"));
		SingleSubmitListDTO sDTO_2 = new SingleSubmitListDTO();
		sDTO_2.setPhone(mashi);
		sDTO_2.setContent(URLEncoder.encode(singleShotListContent, "utf-8"));
		SingleSubmitListDTO sDTO_3 = new SingleSubmitListDTO();
		sDTO_3.setPhone(liufengsheng);
		sDTO_3.setContent(URLEncoder.encode(singleShotListContent, "utf-8"));
		list.add(sDTO_1);
		list.add(sDTO_2);
		list.add(sDTO_3);
		String state_02 = getICXFMobileSMSService().singleShotSMSCollection(list);
		BatchsubmitDTO bDTO = new BatchsubmitDTO();
		bDTO.setMsgcontent(URLEncoder.encode(batchShotContent, "utf-8"));
		List<String> phones = new ArrayList<String>();
		phones.add(phone);
		phones.add(mashi);
		phones.add(liufengsheng);
		bDTO.setPhones(phones);
		String state_03 = getICXFMobileSMSService().batchSubmit(bDTO);
		
		this.responResult = JsonUtil.toJson(new StringBuilder()
				.append(state_01).append(state_02).append(state_03).toString());
		return super.defaultExecute();
	}
	
	@Override
	public String getResponState() {
		return responState;
	}

	@Override
	public String getResponResult() {
		return responResult;
	}
	
	private ICXFMobileSMSService getICXFMobileSMSService() {
		return (ICXFMobileSMSService) BeanFactory.getBean("cxfClient");
	}
}
