package com.ldu.my.dao;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.ldu.my.model.User;

@Component("loginDao")
public class LoginDao {
	private HibernateTemplate hibernateTemplate;
	public void save(User user){
		System.out.println(user.getUserName()+"---"+user.getPassWord());
		hibernateTemplate.save(user);
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
