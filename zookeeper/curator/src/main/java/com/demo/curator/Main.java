package com.demo.curator;

import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingServer;

public class Main {

	DistributedAtomicLong distributedAtomicLong;
	TestingServer testingServer;
	TestingCluster testingCluster;
	InterProcessSemaphoreMutex interProcessSemaphoreMutex;
	InterProcessLock interProcessLock;
	InterProcessMutex interProcessMutex;
	InterProcessReadWriteLock interProcessReadWriteLock;
}
