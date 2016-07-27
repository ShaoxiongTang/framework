package com.demo.service;

import java.sql.Connection;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.springframework.stereotype.Service;

import com.demo.entity.User;

@Service
public class UserService {
	
	public void save(User user){
		UserTransaction userTransaction=null;
		try {
			Context ctx = new InitialContext();
			DataSource dataSourceA = (DataSource) ctx.lookup("java:comp/env/jdbc/dataSourceA");  
		    DataSource dataSourceB = (DataSource) ctx.lookup("java:comp/env/jdbc/dataSourceB");  
		    userTransaction = (UserTransaction) ctx.lookup("java:comp/UserTransaction"); 
		    userTransaction.begin();
		    
		    Connection connA=dataSourceA.getConnection();
			Connection connB=dataSourceB.getConnection();
			
			Statement statementA=connA.createStatement();
			Statement statementB=connB.createStatement();
			
			String sqlA="insert into user(name,age) values('"+user.getName()+"',"+user.getAge()+")";
			String sqlB="insert into log(name,age) values('"+user.getName()+"',"+user.getAge()+")";
			
			statementA.execute(sqlA);
			statementB.execute(sqlB);
			
			userTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(userTransaction!=null){
				try {
					userTransaction.rollback();
				} catch (IllegalStateException | SecurityException
						| SystemException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
