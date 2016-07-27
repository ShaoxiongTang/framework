package com.demo.lock.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import org.junit.Test;

public class CountForkJoinTest {
	
	private CountForkJoin task=new CountForkJoin(1,2000);
	private ForkJoinPool forkJoinPool=new ForkJoinPool();

	@Test
	public void testNormalAdd(){
		long start=System.currentTimeMillis();
		
		System.out.println("结果为："+task.addAll());
		
		long end=System.currentTimeMillis();
		System.out.println("耗费时间："+(end-start));
	}
	
	@Test
	public void testForkJoinAdd() throws InterruptedException, ExecutionException{
		long start=System.currentTimeMillis();
		
		ForkJoinTask<Integer> result=forkJoinPool.submit(task);
		System.out.println("结果为："+result.get());
		
		long end=System.currentTimeMillis();
		System.out.println("耗费时间："+(end-start));
	}
}
