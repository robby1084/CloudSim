package org.iti.redis.main;

import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.iti.redis.entity.SMSEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

@Controller("RedisForList")
@Scope("prototype")
public class RedisForList extends AbstractHttpInterfaceAction {

	/**
	 * redis list
	 */
	private static final long serialVersionUID = -5650098773230642246L;
	
	@Autowired
	private RedisTemplate<String, SMSEntity> redisTemplate;
	
	@Override
	public String defaultExecute() throws Throwable {

		redisTemplate.opsForList().rightPush("smsQueue1", new SMSEntity(1,"18892273099","hello",0));
		redisTemplate.opsForList().rightPush("smsQueue1", new SMSEntity(1,"18892273099","hello",0));
		redisTemplate.opsForList().rightPush("smsQueue1", new SMSEntity(1,"18892273099","hello",0));
		redisTemplate.opsForList().rightPush("smsQueue1", new SMSEntity(2,"15122939136","world",1));
		redisTemplate.opsForList().rightPush("smsQueue1", new SMSEntity(2,"15122939136","world",1));
		redisTemplate.opsForList().rightPush("smsQueue1", new SMSEntity(2,"15122939136","world",1));
		redisTemplate.opsForList().rightPush("smsQueue1", new SMSEntity(3,"333","world",1));
		redisTemplate.opsForList().rightPush("smsQueue1", new SMSEntity(4,"444","world",1));
		redisTemplate.opsForList().rightPush("smsQueue1", new SMSEntity(5,"555","world",1));
		redisTemplate.opsForList().rightPush("smsQueue1", new SMSEntity(6,"666","world",1));
		redisTemplate.opsForList().rightPush("smsQueue1", new SMSEntity(7,"777","world",1));
		redisTemplate.opsForList().rightPush("smsQueue1", new SMSEntity(8,"888","world",1));
		redisTemplate.opsForList().rightPush("smsQueue1", new SMSEntity(9,"999","world",1));
		redisTemplate.boundListOps("smsQueue1").rightPush(new SMSEntity(10,"phone","content",2));
		redisTemplate.boundListOps("smsQueue1").leftPush(new SMSEntity(11,"phone","content",2));
		
		System.out.println(redisTemplate.boundListOps("smsQueue1").size());
		System.out.println("smsQueue1所有元素:");
		for(SMSEntity e : redisTemplate.boundListOps("smsQueue1").range(0, -1)){
			System.out.println(e.getId()+e.getPhone()+e.getContent()+e.getOperator());
		}
		System.out.println("smsQueue1左边的前7个元素:");
		for(SMSEntity e : redisTemplate.boundListOps("smsQueue1").range(0, 7)){
			System.out.println(e.getId()+e.getPhone()+e.getContent()+e.getOperator());
		}
		System.out.println("smsQueue1左边的索引1-7的元素:");
		for(SMSEntity e : redisTemplate.boundListOps("smsQueue1").range(1, 7)){
			System.out.println(e.getId()+e.getPhone()+e.getContent()+e.getOperator());
		}
		SMSEntity leftone = redisTemplate.boundListOps("smsQueue1").leftPop();
		System.out.println("smsQueue1左边的第一个元素"+leftone.getId()+leftone.getPhone()+leftone.getContent());
		System.out.println(redisTemplate.boundListOps("smsQueue1").size()+"pop出左边第一个元素后，smsQueue1所有元素");
		for(SMSEntity e : redisTemplate.boundListOps("smsQueue1").range(0, -1)){
			System.out.println(e.getId()+e.getPhone()+e.getContent()+e.getOperator());
		}
		SMSEntity rightone = redisTemplate.boundListOps("smsQueue1").rightPop();
		System.out.println("smsQueue1右边的第一个元素"+rightone.getId()+rightone.getPhone()+rightone.getContent());
		System.out.println(redisTemplate.boundListOps("smsQueue1").size()+"pop出右边第一个元素后，smsQueue1所有元素:");
		for(SMSEntity e : redisTemplate.boundListOps("smsQueue1").range(0, -1)){
			System.out.println(e.getId()+e.getPhone()+e.getContent()+e.getOperator());
		}
		redisTemplate.watch("smsQueue1");
		
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
