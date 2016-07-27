package com.demo.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import com.demo.rmi.service.HelloService;
import com.demo.rmi.service.impl.HelloServiceImpl;

public class RMIServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException{
		LocateRegistry.createRegistry(1099);
		HelloService helloService=new HelloServiceImpl();
		Naming.rebind("helloService",helloService);
		System.out.println("Hello Server is ready.");
	}
}
