package com.exadel.democars.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name="carsCollectionBean")
@SessionScoped
public class CarsCollectionBean implements Serializable {
	private static final Car[] cars = new Car[] {
            new Car("Ford", "Scorpio", 100500.0, "ZXCDFG"),
            new Car("Totota", "Corolla", 1000.0, "GSOMQ"),
            new Car("Nissan", "Primera", 25000.2, "TDFFDD")
    };

	public Car[] getCars() {
		return cars;
	}
}
