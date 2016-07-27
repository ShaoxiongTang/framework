package com.demo.sasl.base;

import java.util.Map;
import java.util.TreeMap;

import javax.security.sasl.Sasl;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;

public class SaslDemo {

	public static void main(String[] args) throws SaslException{
		Map<String, String> props = new TreeMap<String, String>();
		props.put(Sasl.QOP, "auth");
		
		SaslServer ss = Sasl.createSaslServer("DIGEST-MD5", "xmpp", "java.com",props, new ServerCallbackHandler());
		byte[] token = new byte[0];
		byte[] challenge = ss.evaluateResponse(token);
		
		SaslClient sc = Sasl.createSaslClient(new String[] { "DIGEST-MD5" },"tony", "xmpp", "java.com", null, new ClientCallbackHandler());
		byte response[];
		
		if (challenge != null) {
			response = sc.evaluateChallenge(challenge);
		} else {
			response = sc.evaluateChallenge(null);
		}
		ss.evaluateResponse(response);
		if (ss.isComplete()) {
			System.out.println("auth success");
		}else{
			System.out.println("auth failed");
		}

	}
}
