package com.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

	@Value("${env}")
	private String env;
	
	public String getEnv(){
		return env;
	}
}
