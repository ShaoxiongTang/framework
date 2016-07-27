package com.demo.dao;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.entity.User;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void save(User user) throws SQLException{
		jdbcTemplate.update("insert into user(name,age) value(?,?)",user.getName(),user.getAge());
	}
}
