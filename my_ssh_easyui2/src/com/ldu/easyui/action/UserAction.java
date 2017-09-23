package com.ldu.easyui.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.ldu.easyui.model.User;
import com.ldu.easyui.service.UserService;
import com.ldu.easyui.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class UserAction extends ActionSupport {
	private UserService userService;
	private List<User> list;
	private String json1;
	private String test;
	
	public String look() {
		System.out.println("test:"+test);
		System.out.println("look before");
		list = userService.lookUser();
		System.out.println("look after");
		Gson gson=new Gson();
		json1=gson.toJson(list);
		
		System.out.println(json1);
		return "success";
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource(name = "userServiceImpl")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	

	public String getJson1() {
		return json1;
	}

	public void setJson1(String json1) {
		this.json1 = json1;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
}
