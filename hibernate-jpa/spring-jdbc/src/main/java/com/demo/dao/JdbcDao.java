package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcDao implements InitializingBean{

	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.user}")
	private String user;
	@Value("${jdbc.password}")
	private String password;
	@Value("${jdbc.driver}")
	private String driver;
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Class.forName(driver);
	}
}
