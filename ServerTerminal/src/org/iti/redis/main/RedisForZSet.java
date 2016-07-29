package org.iti.redis.main;

import java.util.List;
import java.util.Set;

import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.iti.redis.entity.SMSEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

@Controller("RedisForZSet")
@Scope("prototype")
public class RedisForZSet extends AbstractHttpInterfaceAction {

	/**
	 * redis zset
	 */
	private static final long serialVersionUID = 7313631358816516103L;

	@Autowired
	private RedisTemplate<String, SMSEntity> redisTemplate;

	@Override
	public String defaultExecute() throws Throwable {

		redisTemplate.multi();
		redisTemplate.opsForZSet().add("zset1",
				new SMSEntity(1, "phone1", "content1", 1), 2);
		redisTemplate.opsForZSet().add("zset1",
				new SMSEntity(1, "phone1", "content1", 1), 2);
		redisTemplate.opsForZSet().add("zset1",
				new SMSEntity(1, "phone1", "content1", 1), 2);
		redisTemplate.opsForZSet().add("zset1",
				new SMSEntity(2, "phone2", "content2", 2), 1);
		redisTemplate.opsForZSet().add("zset1",
				new SMSEntity(3, "phone3", "content3", 1), 3);
		List<Object> list = redisTemplate.exec();
		for (Object o : list) {
			if (o instanceof SMSEntity) {
				o = (SMSEntity) o;
				System.out.println(((SMSEntity) o).getId()
						+ ((SMSEntity) o).getPhone()
						+ ((SMSEntity) o).getContent());
			}
			System.out.println(o);
		}

		Set<SMSEntity> set = redisTemplate.boundZSetOps("zset1").rangeByScore(
				2, 4);

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
