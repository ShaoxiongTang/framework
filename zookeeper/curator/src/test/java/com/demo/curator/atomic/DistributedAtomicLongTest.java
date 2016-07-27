package com.demo.curator.atomic;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.junit.Before;
import org.junit.Test;

public class DistributedAtomicLongTest {
	
	private CuratorFramework client;

	@Before
	public void init() throws Exception{
		CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();  
		//fluent style  
		String namespace = "ops/sysdev/cms";  
		client = builder.connectString("10.2.27.122:6380")  
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
	public void testAdd(){
		try {
			DistributedAtomicLong distributedAtomicLong=new DistributedAtomicLong(client, "/DistributedAtomicLong/app",new RetryNTimes(3, 1000));
			distributedAtomicLong.initialize(100000L);
			AtomicValue<Long> value=distributedAtomicLong.get();
			System.out.println("post:"+value.postValue());
			value=distributedAtomicLong.increment();
			System.out.println("post:"+value.postValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testSet(){
		try {
			DistributedAtomicLong distributedAtomicLong=new DistributedAtomicLong(client, "/DistributedAtomicLong/app",new RetryNTimes(3, 1000));
			distributedAtomicLong.forceSet(50L);
			AtomicValue<Long> value=distributedAtomicLong.get();
			System.out.println("post:"+value.postValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
