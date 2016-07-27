package com.demo.performance.demo;

import java.util.ArrayList;
import java.util.List;

import com.demo.performance.BaseCallable;
import com.demo.performance.WorkerManager;

public class DemoTest {

	public static void main(String[] args){
		WorkerManager workerManager = new WorkerManager(20);
		List<BaseCallable> callables = new ArrayList<BaseCallable>();
		for(int i=0; i< 100; i++){
			callables.add(new DemoCallable());
		}
		workerManager.start(callables);
	}
}
