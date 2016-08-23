package org.iti.redis.main;

import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.iti.redis.entity.SMSEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

@Controller("RedisForLinkedList")
@Scope("prototype")
public class RedisForLinkedList extends AbstractHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1816958269548113450L;

	@Autowired
	private RedisTemplate<String, SMSEntity> redisTemplate;
	
	@Override
	public String defaultExecute() throws Throwable {

		redisTemplate.boundListOps("smsQueue2").leftPush(new SMSEntity(11,"phone","content",2));
		System.out.println(redisTemplate.boundListOps("smsQueue2").leftPop().toString());
		System.out.println(redisTemplate.boundListOps("smsQueue2").leftPop());
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
}
