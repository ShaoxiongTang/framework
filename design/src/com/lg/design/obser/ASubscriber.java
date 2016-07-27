package com.lg.design.obser;

public class ASubscriber implements Subscriber{

	@Override
	public void receiveNewspaper(String newspaper) {
		System.out.println("A receive newspaper:"+newspaper);
	}

}
