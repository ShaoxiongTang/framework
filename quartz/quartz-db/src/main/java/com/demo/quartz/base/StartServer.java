package com.demo.quartz.base;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartServer {

	public static void main(String[] args) throws IOException{
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-context.xml"});  
        context.start();  
        System.out.println("按任意键退出");  
        System.in.read();
	}
}
