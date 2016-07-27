package com.zookeeper.web.controller;

import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zookeeper.web.service.ZooKeeperService;

@RestController
public class ZooKeeperRestController extends BaseController{

	@Autowired
	private ZooKeeperService zooKeeperService;
	
	@RequestMapping(value="zooKeeper/{rootPath}",method=RequestMethod.GET)
	public List<String> getZooKeeperPathChildren(@PathVariable("rootPath")String rootPath) throws KeeperException, InterruptedException{
		return zooKeeperService.getPath(rootPath);
	}
}
