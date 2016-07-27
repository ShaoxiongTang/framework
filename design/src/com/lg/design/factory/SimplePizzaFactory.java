package com.lg.design.factory;

public class SimplePizzaFactory {

	public Pizza createPizza(String type){
		Pizza pizza;
		if(type==null || type.equals("cheese")){
			pizza=new CheesePizza();
		}else if(type.equals("greek")){
			pizza=new GreekPizza();
		}else{
			pizza=new PepperoniPizza();
		}
		return pizza;
	}
}
