package com.demo.mina.lengthfield;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class TcpServer {

	public static void main(String[] args) throws IOException{
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(
				new PrefixedStringCodecFactory(Charset.forName("UTF-8"))));
		acceptor.setHandler(new TcpServerHandler());  
        acceptor.bind(new InetSocketAddress(8080));  
	}
}
