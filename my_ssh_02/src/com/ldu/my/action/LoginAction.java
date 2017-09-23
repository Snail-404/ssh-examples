package com.ldu.my.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ldu.my.model.User;
import com.ldu.my.service.LoginManager;
import com.opensymphony.xwork2.ActionSupport;

@Component("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport{
	private User user;
	private LoginManager loginManager;
	
	public void lookUser(){
		
	}
	@Override
	public String execute() throws Exception {
		System.out.println(user.getUserName());
		loginManager.add(user);
		return super.execute();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LoginManager getLoginManager() {
		return loginManager;
	}
	@Resource(name="loginManagerImpl")
	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}

}
