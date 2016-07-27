package com.demo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.entity.User;

@Repository
public class UserDao {

	@Autowired
	private HibernateDao hibernateDao;
	
	public void save(User user){
		Session session=hibernateDao.openSession();
		Transaction tx=session.beginTransaction();  
        session.save(user);  
        tx.commit();  
        session.close();  
	}
}
