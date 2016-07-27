package com.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeAction {
	
	@RequestMapping("/")
	public ModelAndView home(){
		return new ModelAndView("index");
	}
	
	@RequestMapping("/a")
	public ModelAndView a(){
		return new ModelAndView("a");
	}
	
	@RequestMapping("/b")
	public ModelAndView b(){
		return new ModelAndView("b");
	}
	
	@RequestMapping("/login.html")
	public ModelAndView login(){
		return new ModelAndView("login");
	}
	
}
