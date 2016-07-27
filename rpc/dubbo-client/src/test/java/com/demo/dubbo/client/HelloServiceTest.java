package com.demo.dubbo.client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.rpc.RpcContext;
import com.demo.dubbo.service.HelloService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class HelloServiceTest {

	@Autowired
    private HelloService helloService;

    @Test
    public void helloTest() {
        String result = helloService.hello("World");
        Assert.assertEquals("Hello World", result);
    }
    
    @Test
    public void helloAysncTest() throws InterruptedException, ExecutionException {
        String result1 = helloService.hello("World");
        String result2 = helloService.hello("java");
        System.out.println("result :"+result1);
        System.out.println("result :"+result2);
        System.out.println("result : "+RpcContext.getContext().getFuture().get());
        System.out.println("result : "+RpcContext.getContext().getFuture().get());
    }
    
    @Test
    public void helloAysncTest2() throws InterruptedException, ExecutionException {
        String result1 = helloService.hello("World");
        Future<String> result1Future=RpcContext.getContext().getFuture();
        String result2 = helloService.hello("java");
        Future<String> result2Future=RpcContext.getContext().getFuture();
        System.out.println("result :"+result1);
        System.out.println("result :"+result2);
        System.out.println("result : "+result1Future.get());
        System.out.println("result : "+result2Future.get());
    }
}
