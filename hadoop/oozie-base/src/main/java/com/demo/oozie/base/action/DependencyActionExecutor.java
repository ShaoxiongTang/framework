package com.demo.oozie.base.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.oozie.action.ActionExecutor;
import org.apache.oozie.action.ActionExecutorException;
import org.apache.oozie.client.WorkflowAction;

public class DependencyActionExecutor extends ActionExecutor{
	
	private final static String TYPE = ":Dependency:";
	
	private ActionExecutor actionExecutor;
	private List<String> dependencies = new ArrayList<String>();

	protected DependencyActionExecutor() {
		super(TYPE);
	}

	@Override
	public void start(Context context, WorkflowAction action)
			throws ActionExecutorException {
		//
	}

	@Override
	public void end(Context context, WorkflowAction action)
			throws ActionExecutorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void check(Context context, WorkflowAction action)
			throws ActionExecutorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kill(Context context, WorkflowAction action)
			throws ActionExecutorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCompleted(String externalStatus) {
		// TODO Auto-generated method stub
		return false;
	}

}
