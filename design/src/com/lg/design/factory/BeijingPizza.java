package com.lg.design.factory;

public class BeijingPizza extends NewPizza{
	
	private PizzaSourceFactory pizzaSourceFactory;
	
	public BeijingPizza(BeijingPizzaSourceFactory pizzaSourceFactory) {
		super();
		this.pizzaSourceFactory = pizzaSourceFactory;
	}

	@Override
	protected void prepare() {
		salt=pizzaSourceFactory.getSalt();
		sugar=pizzaSourceFactory.getSugar();
		flour=pizzaSourceFactory.getFlour();
	}

}
