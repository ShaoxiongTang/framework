package com.lg.design.factory;

public class GreekPizzaFactory extends PizzaStore{

	@Override
	protected Pizza createPizza() {
		return new GreekPizza();
	}

}
