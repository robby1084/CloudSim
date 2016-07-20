package org.iti.sms.client.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.iti.application.context.bean.factory.BeanFactory;
import org.iti.base.rmi.mobilesms.dto.BatchsubmitDTO;
import org.iti.business.preManage.service.IMobileSMSService;
import org.iti.common.util.JsonUtil;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("TestAction3")
@Scope("prototype")
public class TestAction3 extends AbstractHttpInterfaceAction{

	/**
	 * 测试短信发送，群发
	 */
	private static final long serialVersionUID = -7621884323447044303L;

	@Override
	public String defaultExecute() throws Throwable {

		String phone = "15122939136";
		String content = "RMI客户端测试能否调用发送短信群发";
		String content_utf8 = URLEncoder.encode(content, "UTF-8");
		BatchsubmitDTO dto = new BatchsubmitDTO();
		List<String> phones = new ArrayList<String>();
		phones.add(phone);
		phones.add(phone);
		dto.setMsgcontent(content_utf8);
		dto.setPhones(phones);
		
		String state = getMobileSMSService().batchSubmit(dto);
		
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
