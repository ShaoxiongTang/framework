package com.demo.yarn;

import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RpcEngine;
import org.apache.hadoop.mapreduce.v2.app.MRAppMaster;
import org.apache.hadoop.yarn.api.ApplicationClientProtocol;
import org.apache.hadoop.yarn.event.AsyncDispatcher;
import org.apache.hadoop.yarn.ipc.YarnRPC;
import org.apache.hadoop.yarn.server.nodemanager.ContainerExecutor;
import org.apache.hadoop.yarn.server.nodemanager.NodeHealthCheckerService;
import org.apache.hadoop.yarn.server.nodemanager.NodeManager;
import org.apache.hadoop.yarn.server.nodemanager.NodeStatusUpdater;
import org.apache.hadoop.yarn.server.nodemanager.containermanager.AuxServices;
import org.apache.hadoop.yarn.server.nodemanager.containermanager.ContainerManagerImpl;
import org.apache.hadoop.yarn.server.nodemanager.containermanager.application.Application;
import org.apache.hadoop.yarn.server.nodemanager.containermanager.container.Container;
import org.apache.hadoop.yarn.server.nodemanager.containermanager.launcher.ContainersLauncher;
import org.apache.hadoop.yarn.server.nodemanager.containermanager.localizer.ResourceLocalizationService;
import org.apache.hadoop.yarn.server.nodemanager.containermanager.loghandler.LogHandler;
import org.apache.hadoop.yarn.server.nodemanager.containermanager.monitor.ContainersMonitor;
import org.apache.hadoop.yarn.server.resourcemanager.AdminService;
import org.apache.hadoop.yarn.server.resourcemanager.ApplicationMasterService;
import org.apache.hadoop.yarn.server.resourcemanager.ClientRMService;
import org.apache.hadoop.yarn.server.resourcemanager.NMLivelinessMonitor;
import org.apache.hadoop.yarn.server.resourcemanager.NodesListManager;
import org.apache.hadoop.yarn.server.resourcemanager.RMAppManager;
import org.apache.hadoop.yarn.server.resourcemanager.ResourceManager;
import org.apache.hadoop.yarn.server.resourcemanager.ResourceTrackerService;
import org.apache.hadoop.yarn.server.resourcemanager.amlauncher.ApplicationMasterLauncher;
import org.apache.hadoop.yarn.server.resourcemanager.rmapp.RMApp;
import org.apache.hadoop.yarn.server.resourcemanager.rmapp.RMAppImpl;
import org.apache.hadoop.yarn.server.resourcemanager.rmapp.attempt.AMLivelinessMonitor;
import org.apache.hadoop.yarn.server.resourcemanager.rmapp.attempt.RMAppAttempt;
import org.apache.hadoop.yarn.server.resourcemanager.rmapp.attempt.RMAppAttemptImpl;
import org.apache.hadoop.yarn.server.resourcemanager.rmcontainer.ContainerAllocationExpirer;
import org.apache.hadoop.yarn.server.resourcemanager.rmcontainer.RMContainer;
import org.apache.hadoop.yarn.server.resourcemanager.rmcontainer.RMContainerImpl;
import org.apache.hadoop.yarn.server.resourcemanager.scheduler.ResourceScheduler;
import org.apache.hadoop.yarn.server.resourcemanager.scheduler.fifo.FifoScheduler;
import org.apache.hadoop.yarn.server.security.ApplicationACLsManager;
import org.apache.hadoop.yarn.webapp.WebApp;

public class Main {

	ResourceManager resourceManager;
	
	//用户交互
	ClientRMService clientRMService;
	AdminService adminService;
	WebApp webApp;
	
	//NM管理
	NMLivelinessMonitor nMLivelinessMonitor;
	NodesListManager nodesListManager;
	ResourceTrackerService resourceTrackerService;
	
	//AM管理
	AMLivelinessMonitor aMLivelinessMonitor;
	ApplicationMasterLauncher applicationMasterLauncher;
	ApplicationMasterService applicationMasterService;
	
	//Application管理
	ApplicationACLsManager applicationACLsManager;
	RMAppManager rMAppManager;
	ContainerAllocationExpirer containerAllocationExpirer;
	
	//资源分配
	ResourceScheduler resourceScheduler;
	FifoScheduler fifoScheduler;
	
	AsyncDispatcher asyncDispatcher;
	
	RMApp rMApp;
	RMAppImpl rmAppImpl;
	
	RMAppAttempt rMAppAttempt;
	RMAppAttemptImpl rMAppAttemptImpl;
	
	RMContainer rmContainer;
	RMContainerImpl rmContainerImpl;
	
	
	//YARN NodeManager分析
	NodeManager nodeManager;
	NodeStatusUpdater nodeStatusUpdater;
	ContainerManagerImpl containerManager;
	ResourceLocalizationService resourceLocalizationService;
	ContainersLauncher containersLauncher;
	AuxServices auxServices;
	ContainersMonitor containersMonitor;
	LogHandler logHandler;
	ContainerExecutor containerExecutor;
	NodeHealthCheckerService nodeHealthCheckerService;
	
	Application application;
	Container container;
	
	//YARN MRAppMaster分析
	MRAppMaster mrAppMaster;
	
	RPC rpc;
	RpcEngine rpcEngine;
	YarnRPC yarnRPC;
	ApplicationClientProtocol applicationClientProtocol;
}
