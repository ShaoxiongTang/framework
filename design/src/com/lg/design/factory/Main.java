package com.lg.design.factory;

public class Main {

	public static void main(String[] args) {
		
	}
	
	public static Pizza orderPizzaFactory(String type){
		PizzaStore pizzaStore=new CheesePizzaFactory();
		return pizzaStore.orderPizza();
	}
	
	public static Pizza orderPizzaSimpleFactory(String type){
		SimplePizzaFactory factory=new SimplePizzaFactory();
		Pizza pizza=factory.createPizza("cheese");
		
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.bake();
		
		return pizza;
	}
	
	public static Pizza orderPizza(String type){
		Pizza pizza;
		if(type==null || type.equals("cheese")){
			pizza=new CheesePizza();
		}else if(type.equals("greek")){
			pizza=new GreekPizza();
		}else{
			pizza=new PepperoniPizza();
		}
		
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.bake();
		
		return pizza;
	}

}
