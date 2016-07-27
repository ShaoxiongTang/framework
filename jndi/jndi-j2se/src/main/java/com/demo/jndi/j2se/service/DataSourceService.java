package com.demo.jndi.j2se.service;

public interface DataSourceService {

	public MyConnection getConnection();
	
	public void setUrl(String url);
	
}
