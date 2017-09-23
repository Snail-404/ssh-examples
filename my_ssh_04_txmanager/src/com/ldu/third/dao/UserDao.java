package com.ldu.third.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ldu.third.model.User;
//@Component
@Repository
public class UserDao {
	private HibernateTemplate hibernateTemplate;
	private List<User> list;

	public void save(User user){
		hibernateTemplate.save(user);
	}

	public List<User> lookUsers(){
		list=hibernateTemplate.find("from User");
		return list;
	}


	public boolean deleteUser(int id){
		User u=new User();
		u.setId(id);
		System.out.println(id);

		//hibernateTemplate.merge(u);
		hibernateTemplate.clear();
		hibernateTemplate.delete(u);
		hibernateTemplate.flush();

		return true;

	}

	public void updateUser(User user){
		hibernateTemplate.update(user);
	}

	public User find(int id){
		User u=hibernateTemplate.get(User.class, id);
		return u;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
