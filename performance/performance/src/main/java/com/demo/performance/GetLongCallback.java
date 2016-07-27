package com.demo.performance;

import java.util.concurrent.atomic.AtomicLong;

public class GetLongCallback implements GetDataCallback<Long>{

	private MyReference<AtomicLong> reference;
	private String name;
	
	public GetLongCallback(String name, MyReference<AtomicLong> reference) {
		this.reference = reference;
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public Long get(Long index) {
		return reference.get().get();
	}
}
