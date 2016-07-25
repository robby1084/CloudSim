package org.iti.redis.main;

import javax.annotation.Resource;

import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

@Controller("RedisForValue")
@Scope("prototype")
public class RedisForValue extends AbstractHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2530724881165004380L;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public String defaultExecute() throws Throwable {

		redisTemplate.opsForValue().set("stringkey1", "stringValue_1");
		redisTemplate.opsForValue().set("stringkey2", "stringValue_2");
		redisTemplate.opsForValue().set("stringkey1", "stringValue_3");
		
		System.out.println("stringkry1获得的value:"+redisTemplate.opsForValue().get("stringkey1"));
		System.out.println("stringkry2获得的value:"+redisTemplate.opsForValue().get("stringkey2"));
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
