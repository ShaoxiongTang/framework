package com.demo.spring.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan
@EnableAutoConfiguration
public class Application{

	private static boolean run = true;

    private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	@RequestMapping("/")
    public String home() {
        return "Hello Spring boot and spark-sql!";
    }
	
    public static void main(String[] args) throws Exception {
    	Runtime.getRuntime().addShutdownHook(new Thread(){

			@Override
			public void run() {
				System.out.println("ShutdownHook : shutdown");
				run = false;
			}
    		
    	});

        SpringApplication.run(Application.class, args);
        logger.info("start success !");
        while(run){
        	Thread.sleep(20000);
        }
    }
    
}
