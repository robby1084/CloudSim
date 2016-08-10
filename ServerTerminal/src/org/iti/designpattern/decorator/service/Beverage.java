package org.iti.designpattern.decorator.service;

public abstract class Beverage {

	public String description = "Unkown Beverage";

	public String getDescription() {
		return description;
	}
	
	public abstract double cost();
}
