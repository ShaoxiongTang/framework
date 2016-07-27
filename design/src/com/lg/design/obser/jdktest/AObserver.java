package com.lg.design.obser.jdktest;

import java.util.Observable;
import java.util.Observer;

public class AObserver implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		String data=((MyObservable)o).getData();
		System.out.println("A receive data:"+data);
	}

}
