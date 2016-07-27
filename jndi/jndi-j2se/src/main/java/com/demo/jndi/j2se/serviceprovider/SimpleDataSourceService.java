package com.demo.jndi.j2se.serviceprovider;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;

import com.demo.jndi.j2se.service.DataSourceService;
import com.demo.jndi.j2se.service.MyConnection;

public class SimpleDataSourceService implements Referenceable,DataSourceService{
	
	private String url;

	@Override
	public MyConnection getConnection() {
		System.out.println(url);
		return new MyConnection();
	}

	@Override
	public Reference getReference() throws NamingException {
		Reference ref=new Reference(getClass().getName(),SimpleDataSourceServiceFactory.class.getName(),null);
		ref.add(new StringRefAddr("url",url));
		return ref;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
