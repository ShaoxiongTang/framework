package com.demo.syslog.server;

import org.graylog2.syslog4j.server.SyslogServer;
import org.graylog2.syslog4j.server.SyslogServerConfigIF;
import org.graylog2.syslog4j.server.SyslogServerIF;

public class SyslogServerDemo {

	public static void main(String[] args){
		try {
			SyslogServerIF syslogServer = SyslogServer.getInstance("UDP");
			
			SyslogServerConfigIF syslogServerConfig = syslogServer.getConfig();
			syslogServerConfig.setPort(3000);
			syslogServerConfig.addEventHandler(new SyslogEventHandler());
			
			syslogServer.run();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("server closed");
		}
	}
}
