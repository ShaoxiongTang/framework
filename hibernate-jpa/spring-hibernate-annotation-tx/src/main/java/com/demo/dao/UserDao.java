package com.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.User;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(User user){
		Session session=sessionFactory.openSession();
		//Session session=sessionFactory.getCurrentSession();
        session.save(user);  
        throw new RuntimeException();
	}
}
