package com.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;


@Repository
public class HibernateDao {
	
	private SessionFactory sessionFactory;

	@SuppressWarnings("deprecation")
	public HibernateDao(){  
        Configuration config=new Configuration();  
        config.configure("hibernate/base/hibernate.cfg.xml");  
        //config.addClass(User.class);
        //config.addResource("hibernate/mapping/User.cfg.xml");
        sessionFactory=config.buildSessionFactory();  
    }

	public Session openSession(){
		return sessionFactory.openSession();
	}

}
