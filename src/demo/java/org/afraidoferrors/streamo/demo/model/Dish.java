package org.afraidoferrors.streamo.demo.model;

public class Dish {
	String date, code, name, price;
	public Dish(String date, String code, String name, String price) {
		this.date = date;
		this.code = code;
		this.name = name;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return this.date + ": " + this.code + "/" + this.name + "/" + this.price;
	}
}
