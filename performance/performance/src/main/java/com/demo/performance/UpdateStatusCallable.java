package com.demo.performance;

public class UpdateStatusCallable implements Runnable{

	private BaseCallable baseCallable;
	
	public UpdateStatusCallable(BaseCallable baseCallable){
		this.baseCallable = baseCallable;
	}
	
	@Override
	public void run(){
		baseCallable.updateBaseResult();
	}

}