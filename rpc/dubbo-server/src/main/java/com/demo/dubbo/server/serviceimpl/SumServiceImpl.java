package com.demo.dubbo.server.serviceimpl;

import com.demo.dubbo.service.SumService;

public class SumServiceImpl implements SumService{

	@Override
	public int sum(int a, int b) {
		return a+b;
	}

}
