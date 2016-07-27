package com.lg.design.factory;

public abstract class NewPizza {

	protected Salt salt;
	
	protected Sugar sugar;
	
	protected Flour flour;
	
	protected abstract void prepare();
	
	public void bake(){
		System.out.println("bake pizza");
	}
	
	public void cut(){
		System.out.println("cut pizza");
	}
	
	public void box(){
		System.out.println("box pizza");
	}

	public Salt getSalt() {
		return salt;
	}

	public void setSalt(Salt salt) {
		this.salt = salt;
	}

	public Sugar getSugar() {
		return sugar;
	}

	public void setSugar(Sugar sugar) {
		this.sugar = sugar;
	}

	public Flour getFlour() {
		return flour;
	}

	public void setFlour(Flour flour) {
		this.flour = flour;
	}
}
