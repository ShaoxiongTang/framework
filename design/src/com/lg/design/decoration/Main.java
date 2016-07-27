package com.lg.design.decoration;

import java.io.InputStream;

public class Main {

	InputStream in;
	
	public static void main(String[] args){
		ConcreteAComponent concreteAComponent=new ConcreteAComponent();
		
		ConcreteTimeDecorator concreteTimeDecorator=new ConcreteTimeDecorator(concreteAComponent);
		concreteTimeDecorator.fun();
		
		System.out.println("--------------------------------------------");
		ConcretePrintDecorator concretePrintDecorator=new ConcretePrintDecorator(concreteAComponent);
		concretePrintDecorator.fun();
	}
}
