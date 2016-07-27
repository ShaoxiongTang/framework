package com.demo.lock.aqs.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionUseCase {

	private Lock lock=new ReentrantLock();
	private Condition condition=lock.newCondition();
	
	public void conditionWait() throws InterruptedException{
		lock.lock();
		try {
			condition.await();
		}finally{
			lock.unlock();
		}
	}
	
	public void conditionSignal() throws InterruptedException{
		lock.lock();
		try {
			condition.signal();
		}finally{
			lock.unlock();
		}
	}
}
