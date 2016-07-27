package com.lg.design.factory;

public abstract class PizzaStore {

	public Pizza orderPizza(){
		Pizza pizza=createPizza();
		
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.bake();
		
		return pizza;
	}

	protected abstract Pizza createPizza();
}
