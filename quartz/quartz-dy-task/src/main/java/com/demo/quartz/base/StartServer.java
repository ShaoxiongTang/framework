package com.demo.quartz.base;

import java.io.IOException;

import org.quartz.JobDetail;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.MethodInvokingJob;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;

public class StartServer {
	
	JobDetail jobDetail;
	
	//spring job
	MethodInvokingJob methodInvokingJob;
	StatefulMethodInvokingJob statefulMethodInvokingJob;
	
	

	public static void main(String[] args) throws IOException{
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-context.xml"});  
        context.start();  
        System.out.println("按任意键退出");  
        System.in.read();
	}
}
