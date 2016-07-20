package org.iti.sms.client.action;

import java.util.List;

import org.iti.application.context.bean.factory.BeanFactory;
import org.iti.business.entity.UplinkSMS;
import org.iti.business.preManage.service.IMobileSMSService;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("TestAction4")
@Scope("prototype")
public class TestAction4 extends AbstractHttpInterfaceAction{

	/**
	 * 获取上行短信
	 */
	private static final long serialVersionUID = -4082025213844887052L;

	@Override
	public String defaultExecute() throws Throwable {

		List<UplinkSMS> list = getMobileSMSService().getAllUntreatedUplinkSMS();
		
		for(UplinkSMS sms : list){
			System.out.println(sms.getId());
		}
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
