package com.demo.dubbo.server.serviceimpl;

import com.demo.dubbo.service.HelloService;

public class HelloServiceImpl implements HelloService{

	@Override
	public String hello(String msg) {
		return "Hello "+msg;
	}

}
