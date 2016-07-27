package com.demo.mina.sample;

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
		cause.printStackTrace();
		super.exceptionCaught(session, cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// 接收客户端的数据  
        IoBuffer ioBuffer = (IoBuffer) message;  
        byte[] byteArray = new byte[ioBuffer.limit()];  
        ioBuffer.get(byteArray, 0, ioBuffer.limit());  
        System.out.println("messageReceived:" + new String(byteArray, "UTF-8"));  
          
        // 发送到客户端  
        byte[] responseByteArray = "你好".getBytes("UTF-8");  
        IoBuffer responseIoBuffer = IoBuffer.allocate(responseByteArray.length);  
        responseIoBuffer.put(responseByteArray);  
        responseIoBuffer.flip();  
        session.write(responseIoBuffer);  
	}

}
