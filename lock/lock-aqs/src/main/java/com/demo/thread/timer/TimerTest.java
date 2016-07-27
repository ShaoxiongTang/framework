package com.demo.thread.timer;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

public class TimerTest {

	Timer timer;
	TimerTask timerTask;
	
	@Test
	public void testTimer(){
		Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("TimerTask run");
			}
		},1000);
		sleepMs(5000);
	}
	
	private void sleepMs(long ms){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
