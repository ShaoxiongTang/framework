package com.zookeeper.web.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zookeeper.web.domain.ZooKeeperEvent;

public interface ZooKeeperEventRepository extends CrudRepository<ZooKeeperEvent,Long>{

	public List<ZooKeeperEvent> findByPath(String path);
}
