package org.iti.redis.main;

import java.util.Set;

import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.iti.redis.entity.SMSEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

@Controller("RedisForSet")
@Scope("prototype")
public class RedisForSet extends AbstractHttpInterfaceAction {

	/**
	 * redis set
	 */
	private static final long serialVersionUID = 5182888640593823198L;

	@Autowired
	private RedisTemplate<String, SMSEntity> redisTemplate;
	
	@Override
	public String defaultExecute() throws Throwable {

		long a = redisTemplate.opsForSet().add("setKey1", new SMSEntity(1,"phone1","content1",1));
		long b = redisTemplate.opsForSet().add("setKey1", new SMSEntity(2,"phone2","content2",2));
		long c = redisTemplate.opsForSet().add("setKey1", new SMSEntity(4,"phone4","content4",4));
		long d = redisTemplate.opsForSet().add("setKey1", new SMSEntity(5,"phone5","content5",5));
		long e = redisTemplate.opsForSet().add("setKey2", new SMSEntity(1,"phone1","content1",1));
		long f = redisTemplate.opsForSet().add("setKey2", new SMSEntity(3,"phone3","content3",3));
		long g = redisTemplate.opsForSet().add("setKey3", new SMSEntity(1,"phone1","content1",1));
		
		Set<SMSEntity> diffKey2 = redisTemplate.boundSetOps("setKey1").diff("setKey2");
		printResult(diffKey2);
		System.out.println("------");
		System.out.println(redisTemplate.boundSetOps("setKey1").diff("setKey3"));
		Set<SMSEntity> diffKey3 = redisTemplate.boundSetOps("setKey1").diff("setKey3");
		printResult(diffKey3);
		System.out.println("------");
		Set<SMSEntity> unionKey2 = redisTemplate.boundSetOps("setKey1").union("setKey2");
		printResult(unionKey2);
		System.out.println("------");
		Set<SMSEntity> unionKey3 = redisTemplate.boundSetOps("setKey1").union("setKey3");
		printResult(unionKey3);
		System.out.println("------");
		Set<SMSEntity> isectKey2 = redisTemplate.boundSetOps("setKey1").intersect("setKey2");
		printResult(isectKey2);
		System.out.println("------");
		Set<SMSEntity> isectKey3 = redisTemplate.boundSetOps("setKey1").intersect("setKey2");
		printResult(isectKey3);
		System.out.println("------");
		
		System.out.println(redisTemplate.boundSetOps("setKey1").pop());
		System.out.println(redisTemplate.boundSetOps("setKey1").randomMember());
		
		return super.defaultExecute();
	}
	
	private <T> void printResult(Set<T> set){
		
		for(T t : set){
			SMSEntity entity;
			if (t instanceof SMSEntity) {
				entity = (SMSEntity)t;
				System.out.println(entity.toString());
			}
		}
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
