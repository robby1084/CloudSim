package org.iti.sms.client.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.iti.application.context.bean.factory.BeanFactory;
import org.iti.base.rmi.mobilesms.dto.SingleSubmitListDTO;
import org.iti.business.preManage.service.IMobileSMSService;
import org.iti.common.util.JsonUtil;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("TestAction2")
@Scope("prototype")
public class TestAction2 extends AbstractHttpInterfaceAction{

	/**
	 * 测试短信发送，单发多条
	 */
	private static final long serialVersionUID = -6938761268003674806L;

	@Override
	public String defaultExecute() throws Throwable {

		String phone = "15122939136";
		String content = "RMI客户端测试能否调用发送短信单条群发1";
		String content_utf8 = URLEncoder.encode(content, "UTF-8");
		SingleSubmitListDTO dto1 = new SingleSubmitListDTO();
		dto1.setContent(content_utf8);
		dto1.setPhone(phone);
		
		String content2 = "RMI客户端测试能否调用发送短信单条群发2";
		String content2_utf8 = URLEncoder.encode(content2, "UTF-8");
		SingleSubmitListDTO dto2 = new SingleSubmitListDTO();
		dto2.setContent(content2_utf8);
		dto2.setPhone(phone);
		
		List<SingleSubmitListDTO> list = new ArrayList<SingleSubmitListDTO>();
		list.add(dto1);
		list.add(dto2);
		
		String state = getMobileSMSService().singleShotSMSCollection(list);
		
		this.responResult = JsonUtil.toJson(state);
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

	private IMobileSMSService getMobileSMSService(){
		return (IMobileSMSService) BeanFactory.getBean("MobileSMSServiceClient");
	}
}
