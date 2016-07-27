package com.demo.rmi.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.demo.rmi.service.HelloService;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService{

	private static final long serialVersionUID = -4184823695033023475L;

	public HelloServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void say() {
		System.out.println("hello RMI");
	}

}
