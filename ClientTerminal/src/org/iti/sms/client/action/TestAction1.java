package org.iti.sms.client.action;

import java.net.URLEncoder;

import org.iti.application.context.bean.factory.BeanFactory;
import org.iti.business.preManage.service.IMobileSMSService;
import org.iti.common.util.JsonUtil;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("TestAction1")
@Scope("prototype")
public class TestAction1 extends AbstractHttpInterfaceAction{

	/**
	 * 测试短信发送，单发
	 */
	private static final long serialVersionUID = 8797461289386503959L;

	@Override
	public String defaultExecute() throws Throwable {

		String phone = "18892273099";
		String content = "RMI客户端测试能否调用发送短信";
		String content_utf8 = URLEncoder.encode(content, "UTF-8");
		String state = getMobileSMSService().singleShotSMS(phone, content_utf8);
		
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
