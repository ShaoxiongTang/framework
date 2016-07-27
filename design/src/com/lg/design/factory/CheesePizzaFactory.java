package com.lg.design.factory;

public class CheesePizzaFactory extends PizzaStore{

	@Override
	protected Pizza createPizza() {
		return new CheesePizza();
	}

}
