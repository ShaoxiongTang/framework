package com.demo.hive;

public interface HelloService {

	public void hello();
	
	HelloService NOOP = new HelloService() {
		
		public void hello() {
			
		}
	};
}
