package com.lg.design.obser;

import java.util.ArrayList;
import java.util.List;

public class PublishingHouse {
	
	private List<Subscriber> subscribers=new ArrayList<Subscriber>();
	
	public void add(Subscriber subscriber){
		if(subscriber!=null && !subscribers.contains(subscriber)){
			subscribers.add(subscriber);
		}
	}
	
	public void delete(Subscriber subscriber){
		if(subscriber!=null){
			subscribers.remove(subscriber);
		}
	}

	public void produceNewspaper(String newspaper) {
		notifySubscribers(newspaper);
	}

	private void notifySubscribers(String newspaper) {
		for(Subscriber subscriber:subscribers){
			subscriber.receiveNewspaper(newspaper);
		}
	}
	
}
