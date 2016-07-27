package com.demo.performance.demo;

import com.demo.performance.BaseCallable;
import com.demo.performance.Result;
import com.demo.performance.Result.Status;
import com.demo.performance.util.RandomUtils;
import com.demo.performance.util.TimeUtils;

public class DemoCallable extends BaseCallable{
	
	@Override
	public Result call() throws Exception {
		setStatus(Status.PREP);
		TimeUtils.sleep(100);
		setStatus(Status.RUNNING);
		TimeUtils.sleep(200);
		boolean successed = RandomUtils.getRandom() % 2 == 1 ? false:true;
		if(successed){
			setStatus(Status.SUCCEEDED);
		}else{
			setStatus(Status.FAILED);
		}
		return getBaseResult();
	}

}
