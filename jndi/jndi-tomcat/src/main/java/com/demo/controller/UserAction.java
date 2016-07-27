package com.demo.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.UserDao;
import com.demo.entity.User;

@RestController
public class UserAction {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="user/add/{name}/{age}",method=RequestMethod.GET)
	public Object save(@PathVariable("name")String name,@PathVariable("age")Integer age) throws SQLException{
		User user=new User();
		user.setName(name);
		user.setAge(age);
		userDao.save(user);
		return user;
	}
	
}
