package com.demo.thread.execotors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecotorsTest {

	public void testNewFixedThreadPool(){
		ExecutorService executorService=Executors.newFixedThreadPool(3);
		executorService.submit(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		executorService.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
