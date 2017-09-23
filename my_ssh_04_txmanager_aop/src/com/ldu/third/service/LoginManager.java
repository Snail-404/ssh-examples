package com.ldu.third.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ldu.third.model.User;

public interface LoginManager {
	public void add(User user);
	public List<User> lookUsers();
	public boolean delete(int id);
	public void updateUser(User user);
	public User find(int id);
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deladdTest();
}
