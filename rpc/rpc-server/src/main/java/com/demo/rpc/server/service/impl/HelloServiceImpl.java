package com.demo.rpc.server.service.impl;

import com.demo.rpc.server.annotation.RpcService;
import com.demo.rpc.service.HelloService;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService{

	@Override
	public String hello(String msg) {
		return "Hello "+msg;
	}

}
