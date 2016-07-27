package com.demo.oozie.base;

import org.apache.hadoop.mapred.JobClient;
import org.apache.oozie.WorkflowActionBean;
import org.apache.oozie.cli.OozieCLI;
import org.apache.oozie.command.coord.CoordSubmitXCommand;
import org.apache.oozie.command.wf.ActionCheckXCommand;
import org.apache.oozie.BundleJobBean;
import org.apache.oozie.CoordinatorJobBean;
import org.apache.oozie.DagEngine;
import org.apache.oozie.WorkflowJobBean;
import org.apache.oozie.action.ActionExecutor;
import org.apache.oozie.action.hadoop.JavaActionExecutor;
import org.apache.oozie.command.wf.ActionEndXCommand;
import org.apache.oozie.command.wf.ActionStartXCommand;
import org.apache.oozie.command.wf.ActionXCommand;
import org.apache.oozie.command.wf.ResumeXCommand;
import org.apache.oozie.command.wf.SignalXCommand;
import org.apache.oozie.command.wf.StartXCommand;
import org.apache.oozie.command.wf.SubmitXCommand;
import org.apache.oozie.command.wf.SuspendXCommand;
import org.apache.oozie.local.LocalOozie;
import org.apache.oozie.service.ActionCheckerService;
import org.apache.oozie.service.CallableQueueService;
import org.apache.oozie.service.InstrumentationService;
import org.apache.oozie.service.Service;
import org.apache.oozie.service.Services;
import org.apache.oozie.service.WorkflowAppService;
import org.apache.oozie.servlet.BaseJobServlet;
import org.apache.oozie.servlet.BaseJobsServlet;
import org.apache.oozie.servlet.CallbackServlet;
import org.apache.oozie.servlet.V1JobsServlet;
import org.apache.oozie.store.Store;
import org.apache.oozie.util.Instrumentation;
import org.apache.oozie.workflow.WorkflowApp;
import org.apache.oozie.workflow.WorkflowInstance;
import org.apache.oozie.workflow.lite.DBLiteWorkflowLib;
import org.apache.oozie.workflow.lite.LiteWorkflowInstance;

public class Main {

	WorkflowAppService workflowAppService;
	SubmitXCommand submitXCommand;
	LiteWorkflowInstance liteWorkflowInstance;
	DBLiteWorkflowLib dbLiteWorkflowLib;
	Store store;
	Services services;
	Service service;
	WorkflowApp workflowApp;
	WorkflowInstance workflowInstance;
	
	ActionExecutor actionExecutor;
	JavaActionExecutor javaActionExecutor;
	
	CoordinatorJobBean coordinatorJobBean;
	WorkflowJobBean workflowJobBean;
	BundleJobBean bundleJobBean;
	
	org.apache.oozie.cli.OozieCLI cli;
	ActionCheckXCommand actionCheckXCommand;
	BaseJobServlet baseJobServlet;
	BaseJobsServlet baseJobsServlet;
	V1JobsServlet v1JobsServlet;
	
	WorkflowActionBean workflowActionBean;
	SignalXCommand signalXCommand;
	DagEngine dagEngine;
	CallableQueueService callableQueueService;
	
	OozieCLI oozieCLI;
	
	LocalOozie localOozie;
	
	CallbackServlet callbackServlet;
	
	Instrumentation instrumentation;
	InstrumentationService instrumentationService;
	
	ActionStartXCommand actionStartXCommand;
	ActionEndXCommand actionEndXCommand;
	ActionXCommand<?> actionXCommand;
	
	ActionCheckerService actionCheckerService;
	
	ResumeXCommand resumeXCommand;
	SuspendXCommand suspendXCommand;
	StartXCommand startXCommand;
	
	CoordSubmitXCommand coordSubmitXCommand;
	
	JobClient jobClient;
}
