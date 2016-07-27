package com.lg.design.decoration;

public class ConcreteTimeDecorator implements Component{
	
	private Component component;

	public ConcreteTimeDecorator(Component component) {
		super();
		this.component = component;
	}

	@Override
	public void fun() {
		long start=System.currentTimeMillis();
		System.out.println("start at "+start);
		component.fun();
		long end=System.currentTimeMillis();
		System.out.println("end at "+end+",cost "+(end-start));
	}

}
