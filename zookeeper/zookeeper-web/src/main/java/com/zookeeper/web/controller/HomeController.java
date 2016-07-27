package com.zookeeper.web.controller;


import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
	
	Stat sdfd;
	
	@RequestMapping("/")
	public ModelAndView home(){
		return new ModelAndView("index");
	}
	
}
