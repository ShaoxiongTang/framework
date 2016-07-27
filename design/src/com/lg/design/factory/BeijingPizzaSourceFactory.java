package com.lg.design.factory;

public class BeijingPizzaSourceFactory implements PizzaSourceFactory{

	@Override
	public Salt getSalt() {
		return new BeijingSalt();
	}

	@Override
	public Sugar getSugar() {
		return new BeijingSugar();
	}

	@Override
	public Flour getFlour() {
		return new BeijingFlour();
	}

}
