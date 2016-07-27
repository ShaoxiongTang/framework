package com.lg.design.obser.jdktest;

public class ObservableTest {

	public static void main(String[] args){
		final MyObservable myObservable=new MyObservable();
		AObserver aObserver=new AObserver();
		BObserver bObserver=new BObserver();
		myObservable.addObserver(aObserver);
		myObservable.addObserver(bObserver);
		
		Thread t1=new Thread(){

			@Override
			public void run() {
				myObservable.setData("你是谁",1);
				System.err.println("t1 over");
			}
			
		};
		Thread t2=new Thread(){

			@Override
			public void run() {
				myObservable.setData("你是谁",8);
				System.err.println("t2 over");
			}
			
		};
		t1.start();
		t2.start();
	}
}
