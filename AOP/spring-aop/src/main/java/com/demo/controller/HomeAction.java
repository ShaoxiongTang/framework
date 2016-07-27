package com.demo.controller;


import javax.servlet.Servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeAction {
	
	Servlet servlet;
	
	@RequestMapping("/")
	public ModelAndView home(){
		return new ModelAndView("index");
	}
	
}
