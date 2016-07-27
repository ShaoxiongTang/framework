package com.zookeeper.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zookeeper.web.dao.ZooKeeperEventRepository;
import com.zookeeper.web.domain.ZooKeeperEvent;

@Service
public class ZooKeeperEventService {

	@Autowired
	private ZooKeeperEventRepository zooKeeperEventRepository;
	
	public ZooKeeperEvent save(ZooKeeperEvent zooKeeperEvent){
		return zooKeeperEventRepository.save(zooKeeperEvent);
	}
	
	public Iterable<ZooKeeperEvent> findAll(){
		return zooKeeperEventRepository.findAll();
	}
	
	public List<ZooKeeperEvent> findByPath(String path){
		return zooKeeperEventRepository.findByPath(path);
	}
	
}
