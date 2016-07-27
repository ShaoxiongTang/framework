package com.demo.mina.lengthfield;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class TcpServerHandler extends IoHandlerAdapter{

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println(session.getId()+":sessionCreated");
		super.sessionCreated(session);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println(session.getId()+":sessionClosed");
		super.sessionClosed(session);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		super.exceptionCaught(session, cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// 接收客户端的数据，这里接收到的不再是IoBuffer类型，而是字符串  
        String line = (String) message;  
        System.out.println("messageReceived:" + line);  
          
        // 发送到客户端  
        byte[] responseByteArray = "你好".getBytes("UTF-8");  
        IoBuffer responseIoBuffer = IoBuffer.allocate(responseByteArray.length);  
        responseIoBuffer.put(responseByteArray);  
        responseIoBuffer.flip();  
        session.write(responseIoBuffer);  
	}

}
