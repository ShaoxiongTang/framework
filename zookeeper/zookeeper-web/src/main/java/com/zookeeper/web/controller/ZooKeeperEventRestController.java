package com.zookeeper.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zookeeper.web.domain.ZooKeeperEvent;
import com.zookeeper.web.service.ZooKeeperEventService;

@RestController
public class ZooKeeperEventRestController extends BaseController{

	@Autowired
	private ZooKeeperEventService zooKeeperEventService;
	
	@RequestMapping(value="zooKeeperEvent/",method=RequestMethod.GET)
	public Iterable<ZooKeeperEvent> findAll(){
		return zooKeeperEventService.findAll();
	}
	
	@RequestMapping(value="zooKeeperEvent/{path}",method=RequestMethod.GET)
	public List<ZooKeeperEvent> findByPath(@PathVariable("path")String path){
		return zooKeeperEventService.findByPath(path);
	}
}
