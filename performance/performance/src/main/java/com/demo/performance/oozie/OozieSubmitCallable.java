package com.demo.performance.oozie;

import java.util.Map;

import com.demo.performance.BaseCallable;
import com.demo.performance.Result;
import com.demo.performance.Result.Status;

public class OozieSubmitCallable extends BaseCallable{
	
    private String jobId = null;
    
    private String jobPath;
    private Map<String, String> params = null;
    public OozieSubmitCallable(String jobPath, Map<String, String> params) {
    	this.jobPath = jobPath;
    	this.params = params;
    }
    
    public OozieSubmitCallable(String jobPath) {
    	this.jobPath = jobPath;
    }
    
	@Override
	public Result call() throws Exception {
		setStatus(Status.INIT);
		try {
			jobId = OozieUtils.runjob(jobPath, params);
			if(jobId != null){
				setStatus(Status.SUCCEEDED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setStatus(Status.FAILED);
		}
		return getBaseResult();
	}

}
