package com.demo.performance.oozie;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.OozieClientException;
import org.apache.oozie.client.WorkflowJob;
import org.apache.oozie.client.WorkflowJob.Status;

public class OozieUtils {

	private static String OOZIE_URL = "http://10.142.78.40:11000/oozie";
    private static String JOB_Tracker = "10.142.78.36:8032";
    private static String NAMENode = "hdfs://ns";
    
    private static OozieClient client = new OozieClient(OOZIE_URL);
    
    public static OozieClient getClient(){
    	return client;
    }
    
    public static Status getJobStatus(String jobId) throws OozieClientException{
    	WorkflowJob job = client.getJobInfo(jobId);
    	return job.getStatus();
    }
    
    public static String runjob(String jobPath, Map<String, String> params) throws OozieClientException{
    	if(params == null){
    		params = new HashMap<String,String>();
    	}
		params.put("businessDate", "201604051754");
		
		System.setProperty("user.name","admin");
		Properties conf = client.createConfiguration();
		conf.setProperty(OozieClient.APP_PATH, jobPath);
		conf.setProperty("jobTracker", JOB_Tracker);
		conf.setProperty("nameNode", NAMENode);
		if((params != null) && (params.size() > 0)){
		    for(String parameter : params.keySet())
		        conf.setProperty(parameter, params.get(parameter));
		}
		return client.run(conf);
    }
    
}
