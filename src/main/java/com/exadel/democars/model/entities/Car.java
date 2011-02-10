package com.exadel.democars.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {
	@Id @GeneratedValue
	Integer id;
	Integer model;
	Integer price;
	Double mileage;
	String vin;
}
