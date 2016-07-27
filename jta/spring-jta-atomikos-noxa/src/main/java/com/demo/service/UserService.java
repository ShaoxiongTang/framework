package com.demo.service;

import java.sql.Connection;

import javax.resource.spi.XATerminator;
import javax.sql.DataSource;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import javax.transaction.xa.XAResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atomikos.icatch.CompositeTransaction;
import com.atomikos.icatch.imp.CompositeTransactionImp;
import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import com.atomikos.jdbc.nonxa.AtomikosNonXAParticipant;
import com.demo.dao.LogDao;
import com.demo.dao.UserDao;
import com.demo.entity.User;

@Service
public class UserService {
	
	UserTransaction userTransaction;
	TransactionManager transactionManager;
	
	XAResource xAResource;
	XADataSource xADataSource;
	XAConnection xAConnection;
	
	DataSource dataSource;
	Connection connection;
	
	AtomikosNonXADataSourceBean asdfsfsd;
	
	XATerminator asdsfsd;
	
	CompositeTransactionImp  sdfdf;
	
	CompositeTransaction sfsdf;
	
	AtomikosNonXAParticipant asfsdfd;

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
