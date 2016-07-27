package com.lg.design.decoration;

public class ConcretePrintDecorator implements Component{

	private Component component;

	public ConcretePrintDecorator(Component component) {
		super();
		this.component = component;
	}

	@Override
	public void fun() {
		System.out.println("before real component run");
		component.fun();
		System.out.println("after real component run");
	}

}
