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
		Transaction tx=null;
		try {
			tx=session.beginTransaction();  
	        session.save(user);  
	        tx.commit();  
		} catch (Exception e) {
			if(tx!=null){
				tx.rollback();
			}
		}finally{
			session.close();
		}
	}
	
	//hibernate默认把事务的自动提交设置为false，所以这个方法不会执行成功
	public void saveFaild(User user){
		Session session=hibernateDao.openSession();
		session.save(user);
	}
}
