package com.demo.jndi.j2se.serviceprovider;

import com.demo.jndi.j2se.service.DataSourceService;
import com.demo.jndi.j2se.service.MyConnection;

public class SimpleDataSourceService implements DataSourceService{
	
	private String url;

	@Override
	public MyConnection getConnection() {
		System.out.println(url);
		return new MyConnection();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
