package com.demo.lock.sync;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SynchronizedTest {
	
	public static void main(String[] args){
		SynchronizedTest demo=new SynchronizedTest();
		demo.startGet();
		demo.startSet();
		SynchronizedTest.sleepMs(5000);
	}
	
	
	public void startGet(){
		Thread t=new Thread(){

			@Override
			public void run() {
				get();
			}
			
		};
		t.start();
	}
	
	public void startSet(){
		Thread t=new Thread(){

			@Override
			public void run() {
				set();
			}
			
		};
		t.start();
	}

	public synchronized void get(){
		System.out.println("get start:"+getCurrentDate());
		sleepMs(4000);
		System.out.println("get");
		System.out.println("get end:"+getCurrentDate());
	}
	
	public synchronized void set(){
		System.out.println("set start:"+getCurrentDate());
		sleepMs(4000);
		System.out.println("set");
		System.out.println("set start:"+getCurrentDate());
	}
	
	public String getCurrentDate(){
		Date date=new Date(System.currentTimeMillis());
		DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}
	
	public static void sleepMs(long ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
