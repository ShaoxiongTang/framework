package com.demo.service;

import java.sql.Connection;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.Synchronization;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import javax.transaction.xa.XAResource;

import org.enhydra.jdbc.pool.GenericPool;
import org.enhydra.jdbc.pool.StandardPoolDataSource;
import org.enhydra.jdbc.pool.StandardXAPoolDataSource;
import org.enhydra.jdbc.standard.StandardDataSource;
import org.enhydra.jdbc.standard.StandardXAConnectionHandle;
import org.enhydra.jdbc.standard.StandardXADataSource;
import org.objectweb.jotm.TransactionImpl;
import org.objectweb.jotm.UserTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.LogDao;
import com.demo.dao.UserDao;
import com.demo.entity.User;

@Service
public class UserService {
	
	Transaction transaction;
	TransactionImpl transactionImpl;
	UserTransaction userTransaction;
	TransactionManager transactionManager;
	Synchronization synchronization;
	
	XAResource xAResource;
	XADataSource xADataSource;
	XAConnection xAConnection;
	
	DataSource dataSource;
	Connection connection;
	
	ConnectionPoolDataSource dfsfds;
	
	UserTransactionFactory userTransactionFactory;
	org.objectweb.jotm.datasource.DataSourceFactory dataSourceFactory;
	
	PlatformTransactionManager asds;
	
	GenericPool afsdfds;
	StandardDataSource asdfdsfd;
	StandardXADataSource ASFDGD;
	StandardXAPoolDataSource FSDGFDGFD;
	StandardPoolDataSource asfsdfdd;
	
	StandardXAConnectionHandle adsfds;

	@Autowired
	private UserDao userDao;
	@Autowired
	private LogDao logDao;
	
	@Transactional
	public void save(User user){
		userDao.save(user);
		logDao.save(user);
		throw new RuntimeException();
	}
}
