package com.lg.design.obser.jdktest;

import java.util.Observable;

import com.lg.util.TimeUtil;

public class MyObservable extends Observable{

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data,int time) {
		this.data = data;
		setChanged();
		TimeUtil.sleep(time);
		notifyObservers();
	}
	
	
}
