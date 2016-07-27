package com.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

@Repository
public class JpaDao {

	private EntityManagerFactory entityManagerFactory;
	
	public JpaDao(){
		entityManagerFactory=Persistence.createEntityManagerFactory("test");
	}
	
	public EntityManager getEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
}
