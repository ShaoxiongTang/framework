package com.lg.design.obser.impl;

import com.lg.design.obser.impltest.AObserver;
import com.lg.design.obser.impltest.BObserver;
import com.lg.design.obser.impltest.MyObservable;

public class Main {

	public static void main(String[] args){
		MyObservable myObservable=new MyObservable();
		AObserver aObserver=new AObserver();
		BObserver bObserver=new BObserver();
		myObservable.addObserver(aObserver);
		myObservable.addObserver(bObserver);
		
		
		myObservable.setData("data1");
		myObservable.setData("data2");
		
		myObservable.setNotifyOrder(new NormalNotifyOrder());
		System.out.println("-------------------------------------");
		myObservable.setData("data3");
		
	}
}
