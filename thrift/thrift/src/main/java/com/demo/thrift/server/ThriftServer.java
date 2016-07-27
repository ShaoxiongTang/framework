package com.demo.thrift.server;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import com.demo.thrift.service.AdditionService;
import com.demo.thrift.service.impl.AdditionServiceHandler;

public class ThriftServer {

	public static void startSimpleServer(AdditionService.Processor<AdditionServiceHandler> processor) {  
		  try {  
			  TServerTransport serverTransport = new TServerSocket(9090);  
			  TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));  
			   
			  // Use this for a multithreaded server  
			  // TServer server = new TThreadPoolServer(new  
			  // TThreadPoolServer.Args(serverTransport).processor(processor));  
			   
			  System.out.println("Starting the simple server...");  
			  server.serve();  
		  }catch(Exception e) {  
			  e.printStackTrace();  
		  }  
	}  
		    
	public static void main(String[] args) {  
		startSimpleServer(new AdditionService.Processor<AdditionServiceHandler>(new AdditionServiceHandler()));  
	}  
}
