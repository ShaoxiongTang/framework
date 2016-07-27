package com.demo.thrift.service.impl;

import org.apache.thrift.TException;

import com.demo.thrift.service.AdditionService;

public class AdditionServiceHandler implements AdditionService.Iface{

	@Override
	public int add(int n1, int n2) throws TException {
		return n1+n2;
	}

}
