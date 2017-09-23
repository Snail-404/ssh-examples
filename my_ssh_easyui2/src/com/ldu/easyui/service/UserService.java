package com.ldu.easyui.service;

import java.util.List;

import com.ldu.easyui.model.User;

public interface UserService {
	public List<User> lookUser();
	public String register();
}
