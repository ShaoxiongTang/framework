package com.lg.design.obser;

public class Main {

	public static void main(String[] args){
		PublishingHouse publishingHouse=new PublishingHouse();
		Subscriber a=new ASubscriber();
		Subscriber b=new BSubscriber();
		publishingHouse.add(a);
		publishingHouse.add(b);
		
		publishingHouse.produceNewspaper("第一天的报纸");
		publishingHouse.produceNewspaper("第二天的报纸");
	}
}
