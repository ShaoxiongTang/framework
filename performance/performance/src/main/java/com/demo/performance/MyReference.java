package com.demo.performance;

public class MyReference<T> {

	private T t;
	
	public MyReference(T t){
		this.t = t;
	}
	
	public T get(){
		return t;
	}
	
	public void set(T t){
		this.t = t;
	}
}
