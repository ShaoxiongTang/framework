package com.demo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.demo.entity.User;

@Repository
public class UserDao {

	@Resource(name="dataSourceA")
	private DataSource dataSourceA;
	
	@Resource(name="dataSourceB")
	private DataSource dataSourceB;
	
	public void save(User user) throws SQLException{
		Connection connA=dataSourceA.getConnection();
		Connection connB=dataSourceB.getConnection();
		
		Statement statementA=connA.createStatement();
		Statement statementB=connB.createStatement();
		
		String sql="insert into user(name,age) values('"+user.getName()+"',"+user.getAge()+")";
		
		try {
			connA.setAutoCommit(false);
			connB.setAutoCommit(false);
			
			statementA.execute(sql);
			statementB.execute(sql);
			
			//throw new RuntimeException();

			connA.commit();
			connB.commit();
		} catch (Exception e) {
			e.printStackTrace();
			statementA.close();
			statementB.close();
			connA.rollback();
			connB.rollback();
		}finally{
			connA.close();
			connB.close();
		}
	}
}
