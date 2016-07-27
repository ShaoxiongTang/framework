package com.demo.performance.oozie;

import java.util.ArrayList;
import java.util.List;

import com.demo.performance.BaseCallable;
import com.demo.performance.WorkerManager;

public class OozieTest {
	
	private static String JOB_PATH1 = "hdfs://ns/user/hue/oozie/workspaces/hue-oozie-1459414078.05";
	private static String JOB_PATH2 = "hdfs://ns/user/hue/oozie/workspaces/lg-oozie-00001";

	public static void main(String[] args){
		WorkerManager workerManager = new WorkerManager(100);
		List<BaseCallable> callables = new ArrayList<BaseCallable>();
		for(int i=0; i< 5000 ; i++){
			callables.add(new OozieAllCallable(JOB_PATH2));
			//callables.add(new OozieSubmitCallable(JOB_PATH1));
		}
		for(int i=0; i< 500 ; i++){
			//callables.add(new OozieAllCallable(JOB_PATH2));
			//callables.add(new OozieSubmitCallable(JOB_PATH2));
		}
		workerManager.start(callables);
	}
}
