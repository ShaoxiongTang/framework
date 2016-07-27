package com.demo.dubbo.server.wrapper;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.alibaba.dubbo.common.bytecode.NoSuchMethodException;
import com.alibaba.dubbo.common.bytecode.Wrapper;

public class WrapperTest {

	@Test
	public void test1() throws NoSuchMethodException, InvocationTargetException{
		HelloServiceImpl helloServiceImpl=new HelloServiceImpl();
		Wrapper wrapper=Wrapper.getWrapper(HelloServiceImpl.class);
		wrapper.invokeMethod(helloServiceImpl,"hello",new Class[]{String.class},new Object[]{"lg"});
	}
}
