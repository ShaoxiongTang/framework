package com.demo.dubbo.server.wrapper;

public class HelloServiceImpl implements HelloService{
	
	private String name;

	@Override
	public void hello(String msg) {
		System.out.println("hello "+msg);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
