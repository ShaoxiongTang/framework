package com.demo.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class GetRateCallback implements GetDataCallback<Double>{

	private List<MyReference<AtomicLong>> references;
	private String name;
	private Double preValue = 0d;
	private Double currentValue = 0d;
	
	private Long preIndex = 0L;
	private Long currentIndex = 0L;
	
	public GetRateCallback(String name, List<MyReference<AtomicLong>> references) {
		this.name = name;
		this.references = references;
	}
	
	public GetRateCallback(String name, MyReference<AtomicLong> reference) {
		this.name = name;
		this.references = new ArrayList<MyReference<AtomicLong>>();
		this.references.add(reference);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Double get(Long index) {
		preValue = currentValue;
		preIndex = currentIndex;
		currentIndex = index;
		Double total = 0d;
		for(MyReference<AtomicLong> item : references){
			total += Double.parseDouble(item.get().get()+""); 
		}
		currentValue = total;
		return (total - preValue) / (currentIndex - preIndex);
	}

}
