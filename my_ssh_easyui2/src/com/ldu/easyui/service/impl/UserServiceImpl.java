package com.ldu.easyui.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ldu.easyui.dao.UserDao;
import com.ldu.easyui.model.User;
import com.ldu.easyui.service.UserService;

@Component
public class UserServiceImpl implements UserService{
	UserDao userDao;
	
	public List<User> lookUser() {
		
		return userDao.lookUser();
	}

	public String register() {
		// TODO Auto-generated method stub
		return null;
	}

	public UserDao getUserDao() {
		return userDao;
	}
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}