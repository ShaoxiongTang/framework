package com.demo.dubbo.demo.spi.service.impl;

import com.demo.dubbo.demo.spi.service.HelloService;

public class DefaultHelloService implements HelloService{

	@Override
	public void hello() {
		System.out.println("DefaultHelloService say hello");
	}

}
