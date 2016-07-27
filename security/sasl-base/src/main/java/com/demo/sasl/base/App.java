package com.demo.sasl.base;

import javax.security.sasl.Sasl;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslClientFactory;
import javax.security.sasl.SaslServer;
import javax.security.sasl.SaslServerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	
	Sasl sasl;
	
	SaslServer saslServer;
	SaslServerFactory saslServerFactory;
	
	
	SaslClient saslClient;
	SaslClientFactory saslClientFactory;
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
