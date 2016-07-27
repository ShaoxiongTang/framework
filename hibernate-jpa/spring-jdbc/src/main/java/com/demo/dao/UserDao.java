package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.entity.User;

@Repository
public class UserDao {

	@Autowired
	private JdbcDao jdbcDao;
	
	public void save(User user) throws SQLException{
		Connection conn=jdbcDao.getConnection();
		conn.setAutoCommit(false);
		try {
			PreparedStatement ps=conn.prepareStatement("insert into user(name,age) value(?,?)");
			ps.setString(1,user.getName());
			ps.setInt(2,user.getAge());
			ps.execute();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
}
