package com.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.User;

@Repository
public class UserDao {

	@PersistenceContext(unitName="test1")
	private EntityManager entityManager;
	@PersistenceContext(unitName="test2")
	private EntityManager entityManagerTest2;
	
	@Transactional("transactionManager1")
	public void save(User user){
		entityManager.persist(user);
	}
	@Transactional("transactionManager2")
	public void save2(User user){
		entityManagerTest2.persist(user);
	}
}
