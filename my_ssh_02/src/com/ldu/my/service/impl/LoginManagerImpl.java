package com.ldu.my.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.ldu.my.dao.LoginDao;
import com.ldu.my.model.User;
import com.ldu.my.service.LoginManager;

@Component("loginManagerImpl")
public class LoginManagerImpl implements LoginManager{
	
	LoginDao ld;
	public void add(User user) {
		System.out.println(user.getPassWord());
		ld.save(user);
	}
	public LoginDao getLd() {
		return ld;
	}
	@Resource(name="loginDao")
	public void setLd(LoginDao ld) {
		this.ld = ld;
	}
}

