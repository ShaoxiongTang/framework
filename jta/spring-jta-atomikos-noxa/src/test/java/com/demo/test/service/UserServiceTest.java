package com.demo.test.service;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.entity.User;
import com.demo.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:spring/spring-jdbc.xml","classpath:spring/spring-context.xml"})
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void saveUser() throws SQLException{
		User user=new User();
		user.setName("lg");
		user.setAge(15);
		userService.save(user);
	}
}
