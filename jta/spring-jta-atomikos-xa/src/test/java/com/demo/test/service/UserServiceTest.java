package com.demo.test.service;

import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.entity.User;
import com.demo.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:spring/spring-jdbc.xml","classpath:spring/spring-context.xml"})
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void saveUser() throws SQLException, InterruptedException {
		final User user=new User();
		user.setName("lg");
		user.setAge(11);

		final CountDownLatch startLatch = new CountDownLatch(1);
		for (int i = 0; i <10 ; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						startLatch.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					userService.save(user);
				}
			}).start();
		}
		startLatch.countDown();
		Thread.sleep(100000);
	}
}
