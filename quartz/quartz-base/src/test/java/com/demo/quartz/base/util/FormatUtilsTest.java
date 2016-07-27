package com.demo.quartz.base.util;

import org.junit.Test;
import org.springframework.util.Assert;

public class FormatUtilsTest {

	@Test
	public void testServerCicodeValid(){
		Assert.isTrue(FormatUtils.isCicodeValid("SVR123"));
		Assert.isTrue(FormatUtils.isCicodeValid("VMS123"));
	}
	
	@Test
	public void testGetServerShortCi(){
		String cicode="SVR123HP360";
		Assert.isTrue("SVR123".equals(FormatUtils.getShortCiByCicode(cicode)));
	}
}
