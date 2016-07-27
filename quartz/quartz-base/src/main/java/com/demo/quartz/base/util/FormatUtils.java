package com.demo.quartz.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtils {
	
	private static Pattern cicodePattern=Pattern.compile("(SVR|VMS)\\w+");
	
	private static Pattern shortCiPattern=Pattern.compile("((SVR|VMS)\\d+)\\w+");
	
	private static Pattern ipPattern=Pattern.compile(
				 "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");

	public static boolean isCicodeValid(String cicode){
		if(cicode==null){
			return false;
		}
		Matcher matcher=cicodePattern.matcher(cicode);
		return matcher.matches();
	}
	
	public static String getShortCiByCicode(String cicode){
		if(cicode==null){
			return null;
		}
		Matcher matcher=shortCiPattern.matcher(cicode);
		if(matcher.find()){
			return matcher.group(1);
		}
		return null;
	}
	
	public static boolean isIpValid(String ip){
		if(ip==null){
			return false;
		}
		Matcher matcher=ipPattern.matcher(ip);
		return matcher.matches();
	}
	
}
