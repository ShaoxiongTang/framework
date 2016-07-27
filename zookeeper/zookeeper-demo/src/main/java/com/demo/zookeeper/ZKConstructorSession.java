package com.demo.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class ZKConstructorSession implements Watcher{

	private static CountDownLatch connectedSemaphone=new CountDownLatch(1);

	public static void main(String[] args) throws IOException, InterruptedException {
		ZooKeeper zooKeeper=new ZooKeeper("127.0.0.1:2181",10000,new ZKConstructorSession());
		System.out.println(zooKeeper.getState());
		connectedSemaphone.await();
		System.out.println("ZooKeeper session established");
		long sessionId=zooKeeper.getSessionId();
		byte[] sessionPasswd=zooKeeper.getSessionPasswd();
		System.out.println("sessionId="+sessionId);
		//zooKeeper=new ZooKeeper("192.168.126.130:2181",5000,new ZKConstructorSession(),1l,"test".getBytes());
		
		//zooKeeper=new ZooKeeper("192.168.126.130:2181",5000,new ZKConstructorSession(),sessionId,sessionPasswd);
		
		//Thread.sleep(1000*60);
		Thread.sleep(Integer.MAX_VALUE);
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("my ZKConstructorSession watcher Receive watched event:"+event);
		if(KeeperState.SyncConnected==event.getState()){
			connectedSemaphone.countDown();
		}
	}

}
