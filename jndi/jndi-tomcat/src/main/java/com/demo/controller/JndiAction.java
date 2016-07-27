package com.demo.controller;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.JndiService;

@RestController
public class JndiAction {

	@Autowired
	private JndiService jndiService;
	
	@RequestMapping(value="jndi/test",method=RequestMethod.GET)
	public Object testJndiObjSame() throws NamingException{
		jndiService.testDataSourceSame();
		return true;
	}
}
