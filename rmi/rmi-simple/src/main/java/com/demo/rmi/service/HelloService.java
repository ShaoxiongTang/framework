package com.demo.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloService extends Remote{

	public void say() throws RemoteException;
}
