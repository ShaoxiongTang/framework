package com.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.service.HomeService;


@Controller
public class HomeAction {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeAction.class);
	
	@Autowired
	private HomeService homeService;
	
	@RequestMapping("/")
	public ModelAndView home(){
		String env = homeService.getEnv();
		logger.info("env: "+env+" ; I am index");
		ModelAndView ret = new ModelAndView("index");
		ret.addObject("env",env);
		return ret;
	}
	
}
