package com.demo.lock.aqs.share;

import java.util.concurrent.locks.Lock;

import org.junit.Test;

public class TwinsLockTest {

	@Test
	public void test(){
		final Lock lock=new TwinsLock();
		class Worker extends Thread{
			public void run(){
				System.out.println(Thread.currentThread().getName()+" 线程开始");
				lock.lock();
				try {
					System.out.println(Thread.currentThread().getName()+" 获取到了锁");
					int i=5;
					while(i>0){
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName());
						i--;
					}
				}catch(Exception ex){
					System.out.println(Thread.currentThread().getName()+" 发生异常");
				}finally{
					System.out.println(Thread.currentThread().getName()+" 释放锁");
					lock.unlock();
				}
				System.out.println(Thread.currentThread().getName()+" 线程结束");
			}
		}
		for(int i=0;i<5;i++){
			Worker w=new Worker();
			w.setDaemon(true);
			w.start();
		}
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
