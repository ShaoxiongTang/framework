package com.demo.lock.aqs.fair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class FairAndUnfairTest {
	
	private static ReentrantLock2 fairLock=new ReentrantLock2(true);
	
	private static ReentrantLock2 unfairLock=new ReentrantLock2(false);
	
	@Test
	public void testFair(){
		testLock(fairLock);
	}
	
	@Test
	public void testUnfair(){
		testLock(unfairLock);
	}
	
	private void testLock(final ReentrantLock2 lock){
		for(int i=0;i<40;i++){
			Thread t=new Thread(){

				@Override
				public void run() {
					log(lock);
					log(lock);
				}
				
			};
			t.start();
		}
	}
	
	private void log(ReentrantLock2 lock){
		lock.lock();
		try {
			System.out.println("Lock by "+Thread.currentThread().getId()+";Waiting by "+lock.getQueuedThreadIds());
		}finally{
			lock.unlock();
		}
	}
	
	private static class ReentrantLock2 extends ReentrantLock{
		
		private static final long serialVersionUID = -889222233872748839L;

		public ReentrantLock2(boolean fair){
			super(fair);
		}
		
		public List<Long> getQueuedThreadIds(){
			List<Thread> threads=new ArrayList<Thread>(super.getQueuedThreads());
			List<Long> ids=new ArrayList<Long>();
			for(Thread t:threads){
				ids.add(t.getId());
			}
			Collections.reverse(ids);
			return ids;
		}
	}
}
