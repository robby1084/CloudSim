package org.iti.dubbo.consumer;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iti.application.context.bean.factory.BeanFactory;
import org.iti.common.util.JsonUtil;
import org.iti.dubbo.service.DubboService;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("Consumer")
@Scope("prototype")
public class Consumer extends AbstractHttpInterfaceAction {

	/**
	 * dubbo消费者
	 */
	private static final long serialVersionUID = 7429559320410013851L;

	private static final Log log = LogFactory.getLog(Consumer.class);
	private static volatile ExecutorService POOL = Executors.newCachedThreadPool();
	
	@Override
	public String defaultExecute() throws Throwable {

		for(int i=0; i < 10; i++){
			
			Future<String> future = POOL.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
					return getDubboService().sayHello("chenjunda");
				}
			});
			log.debug(future.get());
		}
		
		this.responResult = JsonUtil.toJson(0);
		return super.defaultExecute();
	}
	
	public DubboService getDubboService(){
		return (DubboService) BeanFactory.getBean("dubboService");
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
