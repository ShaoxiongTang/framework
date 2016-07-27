package com.demo.service;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.naming.java.javaURLContextFactory;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Service;

@Service
public class JndiService {
	
	private JndiTemplate jndiTemplate=new JndiTemplate();
	
	javaURLContextFactory dasdsa;
	
	BasicDataSource asdas;
	BasicDataSourceFactory adfcs;
	
	public void testDataSourceSame() throws NamingException{
		DataSource ds1=(DataSource) jndiTemplate.lookup("java:comp/env/jdbc/testdb");
		DataSource ds2=(DataSource) jndiTemplate.lookup("java:comp/env/jdbc/testdb");
		System.out.println(ds1);
		System.out.println(ds2);
	}
}
