package org.iti.redis.main;

import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.iti.redis.entity.SMSEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

@Controller("RedisForHash")
@Scope("prototype")
public class RedisForHash extends AbstractHttpInterfaceAction {

	/**
	 * redis hash
	 */
	private static final long serialVersionUID = 3595690159210068342L;

	@Autowired
	private RedisTemplate<String, SMSEntity> redisTemplate;
	
	@Override
	public String defaultExecute() throws Throwable {

		//redisTemplate.opsForHash().
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
