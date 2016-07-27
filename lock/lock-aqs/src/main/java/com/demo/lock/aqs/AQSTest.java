package com.demo.lock.aqs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AQSTest {

	Lock lock;
	Condition condition;
	ReentrantLock reentrantLock;
	ReentrantReadWriteLock reentrantReadWriteLock;
	CyclicBarrier cyclicBarrier;
	CountDownLatch countDownLatch;
	
	LockSupport lockSupport;
	HashMap<?,?> hashMap;
	Hashtable<?,?> hashTable;
	ConcurrentHashMap<?,?> currConcurrentHashMap;
	ConcurrentLinkedQueue<?> concurrentLinkedQueue;
	
	ForkJoinTask<?> forkJoinTask;
	
	ArrayList<?> arrayList;
	CopyOnWriteArrayList<?> copyOnWriteArrayList;
	
	AtomicInteger atomicInteger;
	
	AbstractQueuedSynchronizer asa;
	
	Object object;
}
