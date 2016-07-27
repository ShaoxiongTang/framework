package com.demo.performance;

public interface GetDataCallback<V> {

	public String getName();
	
	public V get(Long index);
	
}
