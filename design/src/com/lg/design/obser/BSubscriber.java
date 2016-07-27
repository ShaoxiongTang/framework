package com.lg.design.obser;

public class BSubscriber implements Subscriber{

	@Override
	public void receiveNewspaper(String newspaper) {
		System.out.println("B receive newspaper:"+newspaper);
	}

}
