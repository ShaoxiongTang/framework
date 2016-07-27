package com.zookeeper.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.springframework.util.Assert;

@Entity
@Table(name="zooKeeper_event")
public class ZooKeeperEvent {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String path;
	private KeeperState keeperStatus;
	private EventType eventType;
	
	public ZooKeeperEvent(){
		
	}
	
	public ZooKeeperEvent(WatchedEvent event){
		Assert.notNull(event,"to make ZooKeeperEvent,zookeeper WatchedEvent can not be null");
		this.path=event.getPath();
		this.keeperStatus=event.getState();
		this.eventType=event.getType();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public KeeperState getKeeperStatus() {
		return keeperStatus;
	}
	public void setKeeperStatus(KeeperState keeperStatus) {
		this.keeperStatus = keeperStatus;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
}
