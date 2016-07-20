package org.iti.framework.hebut.testAction;

import java.net.URL;

import org.iti.application.context.bean.factory.BeanFactory;
import org.iti.common.util.JsonUtil;
import org.iti.framework.hebut.jaxws.service.JaxWsServer;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.stereotype.Controller;

@Controller("JaxWsTsetAction")
@Scope("prototype")
public class JaxWsTsetAction extends AbstractHttpInterfaceAction{

	/**
	 * @author chenjunda
	 */
	private static final long serialVersionUID = 6980613996124922995L;

	@Override
	public String defaultExecute() throws Throwable {

		//JaxWsServer jaxWsServer = (JaxWsServer) loadJaxWsBean().getObject();
		//String name = jaxWsServer.loadUserNameByCode("102458");
		
		String name = loadJaxWsBean().loadUserNameByCode("102458");
		
		/*JaxWsPortProxyFactoryBean jaxwsppfb = new JaxWsPortProxyFactoryBean();
		jaxwsppfb.setWsdlDocumentUrl(new URL("http://115.24.161.166:8083/jaxws/JaxWebService?wsdl"));
		jaxwsppfb.setPortName("JaxWsTestPort");
		jaxwsppfb.setNamespaceUri("http://testColeection.hebut.framework.iti.org/");
		jaxwsppfb.setServiceName("JaxWebService");
		jaxwsppfb.setServiceInterface(JaxWsServer.class);
		jaxwsppfb.afterPropertiesSet();
		JaxWsServer s = (JaxWsServer)jaxwsppfb.getObject();
		String name = s.loadUserNameByCode("102458");*/
		
		this.responResult = JsonUtil.toJson(name);
		return AbstractHttpInterfaceAction.SUCCESS;
	}
	
	public JaxWsServer loadJaxWsBean(){
		return (JaxWsServer) BeanFactory.getBean("jawWsService");
	}
	
	@Override
	public String getResponState() {
		return responState;
	}

	@Override
	public String getResponResult() {
		return responResult;
	}

}
