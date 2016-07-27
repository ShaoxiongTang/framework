package com.demo.performance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.alibaba.fastjson.JSON;

public class SampleManager {
	
	//private static final Logger logger = LoggerFactory.getLogger(SampleManager.class);

	private AtomicBoolean started = new AtomicBoolean(false);
	
	private Long count = 1L;
	
	private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
	
	private Map<String,GetDataCallback<?>> callbacks = new HashMap<String,GetDataCallback<?>>();
	
	private List<Long> indexs = new ArrayList<Long>();
	private Map<String,List<Object>> metrixs = new HashMap<String,List<Object>>();
	
	public void start(){
		if(started.compareAndSet(false, true)){
			scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
				
				@Override
				public void run() {
					scan();
				}
			}, 0, 1, TimeUnit.SECONDS);
		}
	}
	
	public void scan(){
		System.out.println();
		System.out.println("第 "+count+" 次执行采样开始----------");
		indexs.add(count);
		for(String callbackName : callbacks.keySet()){
			Object value = callbacks.get(callbackName).get(count);
			System.out.println(callbackName+":"+value);
			List<Object> datas = metrixs.get(callbackName);
			if(datas == null){
				datas = new ArrayList<Object>();
				metrixs.put(callbackName, datas);
			}
			datas.add(value);
		}
		System.out.println("第 "+count+" 次执行采样结束----------");
		System.out.println();
		writeToFile();
		count++;
	}
	
	public void addGetDataCallback(GetDataCallback<?> callback){
		if(!callbacks.containsKey(callback.getName())){
			callbacks.put(callback.getName(), callback);
		}else{
			throw new RuntimeException("the name of callback "+callback.getName()+" already exist");
		}
	}
	
	public void stop(){
		writeToFile();
		scheduledExecutorService.shutdown();
	}
	
	private void writeToFile(){
		Map<String,Object> allMetrixs = new HashMap<String,Object>();
		allMetrixs.put("indexs", indexs);
		allMetrixs.putAll(metrixs);
		String allData = JSON.toJSONString(allMetrixs);
		FileWriter writer = null;
		try {
			File file = new File("C:\\log\\oozie\\test.log");
			writer = new FileWriter(file);
			writer.write(allData);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
