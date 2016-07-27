package com.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.entity.User;

@Repository
public class UserDao {

	@Autowired
	private JpaDao jpaDao;
	
	public void save(User user){
		EntityManager entityManager=jpaDao.getEntityManager();
		EntityTransaction tx=null;
		try {
			tx=entityManager.getTransaction();
			tx.begin();
			entityManager.persist(user);
			tx.commit();
		} catch (Exception e) {
			if(tx!=null){
				tx.rollback();
			}
		}finally{
			entityManager.close();
		}
	}
}
