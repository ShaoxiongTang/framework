package com.lg.design.obser.impltest;

import com.lg.design.obser.impl.CollectionObservable;

public class MyObservable extends CollectionObservable{

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
		notifyObservers("update data");
	}
	
	
}
