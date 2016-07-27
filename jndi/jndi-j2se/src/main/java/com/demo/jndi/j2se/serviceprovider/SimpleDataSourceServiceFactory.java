package com.demo.jndi.j2se.serviceprovider;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

public class SimpleDataSourceServiceFactory implements ObjectFactory{
	
	private SimpleDataSourceService dataSource=new SimpleDataSourceService();

	@Override
	public Object getObjectInstance(Object obj, Name name, Context nameCtx,
			Hashtable<?, ?> environment) throws Exception {
		System.out.println(this);
		 if(obj instanceof Reference){
			 Reference ref=(Reference)obj;
			 String url=(String)ref.get("url").getContent();
			 dataSource.setUrl(url);
		     return dataSource;
	     }
		return null;
	}

}
