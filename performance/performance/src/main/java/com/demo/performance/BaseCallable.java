package com.demo.performance;

import java.util.concurrent.Callable;

import com.demo.performance.Result.Status;

public abstract class BaseCallable implements Callable<Result>{

	private BaseResult baseResult = new BaseResult();
	
	public void updateBaseResult(){
		
	}
	
	public BaseResult getBaseResult(){
		return baseResult;
	}
	
	public void setBaseResult(BaseResult baseResult){
		if(baseResult == null){
			throw new RuntimeException("baseResult can not be null");
		}
		this.baseResult = baseResult;
	}
	
	public Status getStatus(){
		return baseResult.getStatus();
	}
	
	public void setStatus(Status status){
		this.baseResult.setStatus(status);
	}
	
	public boolean isDone(){
		return baseResult.isDone();
	}
}
