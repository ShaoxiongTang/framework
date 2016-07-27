package com.demo.dao;

import java.sql.Statement;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.Status;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.springframework.transaction.PlatformTransactionManager;

public class JtaDao {

	UserTransaction userTransaction;
	TransactionManager transactionManager;
	PlatformTransactionManager pl;
	Transaction transaction;
	ConnectionPoolDataSource  asas;
	DataSource  adssd;
	XADataSource ads;
	Statement asdsafdsf;
	XAConnection asdsdfs;
	Status asdsd;
}
