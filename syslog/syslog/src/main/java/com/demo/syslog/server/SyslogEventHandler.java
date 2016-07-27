package com.demo.syslog.server;

import java.net.SocketAddress;

import org.graylog2.syslog4j.server.SyslogServerEventIF;
import org.graylog2.syslog4j.server.SyslogServerIF;
import org.graylog2.syslog4j.server.SyslogServerSessionEventHandlerIF;

public class SyslogEventHandler implements SyslogServerSessionEventHandlerIF {

	private static final long serialVersionUID = 4911589546629699192L;

	@Override
	public void initialize(SyslogServerIF syslogServer) {
		System.out.println("initialize(SyslogServerIF syslogServer)");
	}

	@Override
	public void destroy(SyslogServerIF syslogServer) {
		System.out.println("destroy(SyslogServerIF syslogServer)");
	}

	@Override
	public Object sessionOpened(SyslogServerIF syslogServer,
			SocketAddress socketAddress) {
		System.out.println("sessionOpened: socketAddress "+socketAddress);
		return null;
	}

	@Override
	public void event(Object session, SyslogServerIF syslogServer,
			SocketAddress socketAddress, SyslogServerEventIF event) {
		System.out.println("event: socketAddress "+socketAddress);
	}

	@Override
	public void exception(Object session, SyslogServerIF syslogServer,
			SocketAddress socketAddress, Exception exception) {
		System.out.println("exception: socketAddress "+socketAddress);
	}

	@Override
	public void sessionClosed(Object session, SyslogServerIF syslogServer,
			SocketAddress socketAddress, boolean timeout) {
		System.out.println("sessionClosed: socketAddress "+socketAddress);
	}

}
