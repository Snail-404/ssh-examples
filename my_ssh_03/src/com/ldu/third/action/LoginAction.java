package com.ldu.third.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ldu.third.model.User;
import com.ldu.third.service.LoginManager;
import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class LoginAction extends ActionSupport{
	private User user;
	private User usre2;
	private User u;
	private LoginManager loginManager;
	private List<User> list;
	private int id;
	
	public String login() throws Exception {
		loginManager.add(user);
		return super.execute();
	}
	
	public String look(){
		list=loginManager.lookUsers();
		return "success";
	}
	
	public String delete(){
		if(loginManager.delete(id))
			return "success";
		else 
			return "fail";
	}
	
	public String update(){
		try {
			User upuser=loginManager.find(11);
			System.out.println(upuser.getUserName());
			upuser.setUserName("dfsfgsgd");
			upuser.setPassWord("6456545");
			loginManager.updateUser(upuser);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("action exception");
			e.printStackTrace();
			return "fail";
		}
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
	@Resource
	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUsre2() {
		return usre2;
	}

	public void setUsre2(User usre2) {
		this.usre2 = usre2;
	}
}
