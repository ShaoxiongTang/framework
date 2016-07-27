package com.demo.performance;


public class BaseResult implements Result{

	private Status status = Status.INIT;
	
	public void setStatus(Status status){
		if(status == null){
			throw new RuntimeException("setStatus(status) can not be null");
		}
		this.status = status;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public boolean isDone() {
		return status == Status.FAILED || status == Status.KILLED ||
				status == Status.SUCCEEDED;
	}

	
}
