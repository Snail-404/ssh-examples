package com.ldu.third.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ldu.third.dao.UserDao;
import com.ldu.third.model.User;
import com.ldu.third.service.LoginManager;

@Component("loginManager")
public class LoginManagerImpl implements LoginManager{
	private UserDao userDao;
	private List<User> list;
	public void add(User user) {
		userDao.save(user);
	}

	public UserDao getUserDao() {
		return userDao;
	}
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> lookUsers() {
		list=userDao.lookUsers();
		return list;
	}

	public boolean delete(int id) {
		
		return userDao.deleteUser(id);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public User find(int id) {
		
		return userDao.find(id);
	}

	public void deladdTest() {
		User u=new User();
		u.setUserName("123");
		u.setPassWord("123");
		System.out.println();
		
		
		userDao.save(u);
		System.out.println("-----------------------------");
		userDao.deleteUser(68);
	}

}
