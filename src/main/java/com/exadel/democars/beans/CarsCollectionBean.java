package com.exadel.democars.beans;

import java.util.ArrayList;
import java.util.Collection;

import com.exadel.democars.beans.Car;

public class CarsCollectionBean {
	private Collection cars;
	
	public CarsCollectionBean() {
		cars = new ArrayList<Car>();
		Car car = new Car();
		car.setId(10);
		car.setMake("Ford");
		car.setMileage(100500.0);
		car.setModel("Scorpio");
		car.setVin("ZXCDFG");
		cars.add(car);
	}

	public Collection getCars() {
		return cars;
	}

	public void setCars(Collection cars) {
		this.cars = cars;
	}
}
