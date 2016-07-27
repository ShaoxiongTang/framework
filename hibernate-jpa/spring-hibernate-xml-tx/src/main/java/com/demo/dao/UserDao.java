package com.demo.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.demo.entity.User;

@Repository
public class UserDao {

	@Autowired
	private HibernateDao hibernateDao;
	
	private PlatformTransactionManager transactionManager;
	
	private JdbcTemplate JdbcTemplate;
	private HibernateTemplate adsa;
	
	public void save(User user){
		Session session=hibernateDao.getSession();
        session.save(user);  
        throw new RuntimeException();
	}
	
	public void saveTemplate(){
		TransactionTemplate template=new TransactionTemplate();
		template.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		template.setTransactionManager(transactionManager);
		template.execute(new TransactionCallback<User>() {

			@Override
			public User doInTransaction(TransactionStatus status) {
				//JdbcTemplate.update(sql1);
				//JdbcTemplate.update(sql2);
				
				//session.save(user1);
				//session.save(user2);
				//adsa.save(entity);
				return null;
			}
			
		});
	}
	
	public void aopTest(){
		
	}
}
