package com.demo.jndi.j2se;

import javax.naming.Context;

import com.demo.jndi.j2se.service.DataSourceService;

public class JNDIClient {

	public static void main(String[] args) throws Exception{
		JNDIContainer container=null;
		container=new JNDIContainer();
		container.init();
	   
		//JNDI客户端使用标准JNDI接口访问命名服务。
		Context ctx=container.getContext();
		DataSourceService db1=(DataSourceService)ctx.lookup("DataSourceService");
		DataSourceService db2=(DataSourceService)ctx.lookup("DataSourceService");
		db1.getConnection().save();
		System.out.println(db1);
		System.out.println(db2);
		System.out.println(db1==db2);
		container.close();
	}
}
