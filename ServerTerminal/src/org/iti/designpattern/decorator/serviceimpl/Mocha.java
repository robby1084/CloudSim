package org.iti.designpattern.decorator.serviceimpl;

import org.iti.designpattern.decorator.service.Beverage;
import org.iti.designpattern.decorator.service.CondimentDecorator;

public class Mocha extends CondimentDecorator{

	Beverage beverage;
	
	public Mocha(Beverage beverage){
		this.beverage = beverage;
	}
	
	@Override
	public String getDescription() {
		return beverage.getDescription()+"Mocha";
	}

	@Override
	public double cost() {
		return 0.20+beverage.cost();
	}

}
