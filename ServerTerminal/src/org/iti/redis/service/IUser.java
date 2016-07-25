package org.iti.redis.service;

import java.util.List;

public interface IUser {

	boolean add(User user);
	
	boolean add(List<User> users);
	
	void delete(String key);
	
	void delete(List<String> keys);
	
	boolean update(User user);
	
	User get(String keyId);
}
