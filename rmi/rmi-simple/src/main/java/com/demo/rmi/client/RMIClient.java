package com.demo.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.demo.rmi.service.HelloService;

public class RMIClient {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException{
		HelloService helloService=(HelloService)Naming.lookup("helloService");
		helloService.say();
	}
}
