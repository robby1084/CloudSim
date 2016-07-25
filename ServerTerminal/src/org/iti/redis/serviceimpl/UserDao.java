package org.iti.redis.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.iti.redis.service.AbstractBaseRedisDao;
import org.iti.redis.service.IUser;
import org.iti.redis.service.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

public class UserDao extends AbstractBaseRedisDao<String, User> implements IUser{

	@Override
	public boolean add(final User user) {

		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {

				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key  = serializer.serialize(user.getId());
				byte[] name = serializer.serialize(user.getName());
				return connection.setNX(key, name);
			}
		});
		return result;
	}

	@Override
	public boolean add(final List<User> users) {
		Assert.notEmpty(users);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				for (User user : users) {
					byte[] key = serializer.serialize(user.getId());
					byte[] name = serializer.serialize(user.getName());
					connection.setNX(key, name);
				}
				return true;
			}
		}, false, true);
		return result;
	}

	@Override
	public void delete(String key) {
		List<String> list = new ArrayList<String>();
		list.add(key);
		delete(list);

	}

	@Override
	public void delete(List<String> keys) {
		redisTemplate.delete(keys);	
	}

	@Override
	public boolean update(final User user) {
		String key = user.getId();
		if (get(key) == null) {
			throw new NullPointerException("数据行不存在, key = " + key);
		}
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(user.getId());
				byte[] name = serializer.serialize(user.getName());
				connection.set(key, name);
				return true;
			}
		});
		return result;
	}

	@Override
	public User get(final String keyId) {
		User result = redisTemplate.execute(new RedisCallback<User>() {
			public User doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(keyId);
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				String name = serializer.deserialize(value);
				return new User(keyId, name, null);
			}
		});
		return result;

	}

}
