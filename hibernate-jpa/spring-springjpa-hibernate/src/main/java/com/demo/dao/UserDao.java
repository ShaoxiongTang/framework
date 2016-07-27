package com.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.User;

@Repository
public class UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void save(User user){
		entityManager.persist(user);
		throw new RuntimeException();
	}
}
