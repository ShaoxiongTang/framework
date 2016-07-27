package com.zookeeper.web.service;

import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zookeeper.web.domain.ZooKeeperEvent;

@Service
public class ZooKeeperService implements InitializingBean,Watcher{
	
	@Value("connectString")
	private String connectString;
	@Value("sessionTimeout")
	private int sessionTimeout;
	
	private ZooKeeper zookeeper;
	@Autowired
	private ZooKeeperEventService zooKeeperEventService;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.zookeeper=new ZooKeeper(connectString, sessionTimeout,this);
	}

	@Override
	public void process(WatchedEvent event) {
		zooKeeperEventService.save(new ZooKeeperEvent(event));
	}
	
	public List<String> getPath(String rootPath) throws KeeperException, InterruptedException{
		if(!StringUtils.hasLength(rootPath)){
			rootPath="/";
		}
		return zookeeper.getChildren(rootPath,true);
	}
	
}
