package org.iti.designpattern.decorator.serviceimpl;

import org.iti.designpattern.decorator.service.Beverage;

public class HouseBlend extends Beverage{

	public HouseBlend(){
		description = "Espresso";
	}

	@Override
	public double cost() {
		return 0.89;
	}
}
