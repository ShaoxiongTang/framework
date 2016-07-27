package com.demo.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.entity.User;

@Repository
public class LogDao {

	@Resource(name="jdbcTemplateB")
	private JdbcTemplate jdbcTemplate;
	
	public void save(User user){
		jdbcTemplate.update("insert into log(name,age) values(?,?)",user.getName(),user.getAge());
	}
}
