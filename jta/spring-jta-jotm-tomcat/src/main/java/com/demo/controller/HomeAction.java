package com.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.entity.User;
import com.demo.service.UserService;


@Controller
public class HomeAction {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public ModelAndView home(){
		return new ModelAndView("index");
	}
	
	@RequestMapping("/user/save")
	@ResponseBody
	public Object saveUser(){
		User user=new User();
		user.setAge(22);
		user.setName("lg");
		userService.save(user);
		return true;
	}
	
}
