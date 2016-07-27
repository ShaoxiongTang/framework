package com.demo.curator.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;

public class LockTest {

	private CuratorFramework client;

	@Before
	public void init() throws Exception{
		CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();  
		//fluent style  
		String namespace = "cms/lock";  
		client = builder.connectString("127.0.0.1:2181")  
		        .sessionTimeoutMs(30000)  
		        .connectionTimeoutMs(30000)  
		        .canBeReadOnly(false)  
		        .retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))  
		        .namespace(namespace)  
		        .defaultData(null)  
		        .build();  
		client.start();
		client.createContainers(namespace);
	}
	
	@Test
	public void testLock(){
		InterProcessMutex mutex=null;
		try {
			mutex=new InterProcessMutex(client,"/app");
			mutex.acquire();
			Thread.sleep(1000*60*10);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(mutex!=null){
				try {
					mutex.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
