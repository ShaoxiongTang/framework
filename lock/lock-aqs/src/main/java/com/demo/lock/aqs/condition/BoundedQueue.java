package com.demo.lock.aqs.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueue<T>{

	private Object[] items;
	private int addIndex,removeIndex,count;
	private Lock lock=new ReentrantLock();
	private Condition notEmpty=lock.newCondition();
	private Condition notFull=lock.newCondition();
	
	public BoundedQueue(int size){
		items=new Object[size];
	}
	
	//ReentrantLock是可重入锁，可以允许某个线程多次获取，但仍然是排他锁，所以下面的++操作不会出现并发问题
	public void add(T t) throws InterruptedException{
		lock.lock();
		try {
			while(count==items.length){
				notFull.await();
			}
			items[addIndex]=t;
			addIndex++;
			if(addIndex==items.length){
				addIndex=0;
			}
			count++;
			notEmpty.signal();
		}finally{
			lock.unlock();
		}
	}
	
	public void remove(T t) throws InterruptedException{
		lock.lock();
		try {
			while(count==0){
				notEmpty.await();
			}
			items[removeIndex]=null;
			removeIndex++;
			if(removeIndex==items.length){
				addIndex=0;
			}
			count--;
			notFull.signal();
		}finally{
			lock.unlock();
		}
	}
	
}
