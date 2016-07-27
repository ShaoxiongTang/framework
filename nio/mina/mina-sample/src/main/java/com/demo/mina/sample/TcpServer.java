package com.demo.mina.sample;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class TcpServer {

	public static void main(String[] args) throws IOException{
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.setHandler(new TcpServerHandler());  
        acceptor.bind(new InetSocketAddress(8080));  
	}
}
