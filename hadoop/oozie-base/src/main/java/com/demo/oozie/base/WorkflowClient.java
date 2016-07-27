package com.demo.oozie.base;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.OozieClientException;
import org.apache.oozie.client.WorkflowJob;
import org.apache.oozie.client.WorkflowJob.Status;

public class WorkflowClient {

	private static String OOZIE_URL = "http://10.142.78.40:11000/oozie";
    private static String JOB_PATH = "hdfs://ns/user/hue/oozie/workspaces/lg-oozie-00001";
    private static String JOB_Tracker = "10.142.78.36:8032";
    private static String NAMENode = "hdfs://ns";

    OozieClient wc = null;

    public WorkflowClient(String url){
        wc = new OozieClient(url);
    }

    public String startJob(String wfDefinition, List<WorkflowParameter> wfParameters)
        throws OozieClientException{

        // create a workflow job configuration and set the workflow application path
    	System.setProperty("user.name","admin");
        Properties conf = wc.createConfiguration();
        conf.setProperty(OozieClient.APP_PATH, wfDefinition);

        // setting workflow parameters
        conf.setProperty("jobTracker", JOB_Tracker);
        conf.setProperty("nameNode", NAMENode);
        if((wfParameters != null) && (wfParameters.size() > 0)){
            for(WorkflowParameter parameter : wfParameters)
                conf.setProperty(parameter.getName(), parameter.getValue());
        }
        // submit and start the workflow job
        return wc.run(conf);
    }

    public Status getJobStatus(String jobID) throws OozieClientException{
        WorkflowJob job = wc.getJobInfo(jobID);
        return job.getStatus();
    }

    public static void main(String[] args) throws OozieClientException, InterruptedException{

        // Create client
        WorkflowClient client = new WorkflowClient(OOZIE_URL);
        // Create parameters
        List<WorkflowParameter> wfParameters = new LinkedList<WorkflowParameter>();
        WorkflowParameter a1 = new WorkflowParameter("oozie.use.system.libpath","true");
        WorkflowParameter a2 = new WorkflowParameter("start_date","2016-03-31T17:52Z");
        WorkflowParameter a3 = new WorkflowParameter("end_date","2099-12-31T00:00Z");
        WorkflowParameter a4 = new WorkflowParameter("businessDate","201604051754");
        
        wfParameters.add(a1);
        wfParameters.add(a2);
        wfParameters.add(a3);
        wfParameters.add(a4);
        // Start Oozing
        String jobId = client.startJob(JOB_PATH, wfParameters);
        Status status = client.getJobStatus(jobId);
        if(status == Status.RUNNING)
             System.out.println("Workflow job running");
        else
             System.out.println("Problem starting Workflow job: "+status);
    }

}
