package com.exadel.democars.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name="car")
@RequestScoped
public class Car implements Serializable {
	Integer id;
	String make;
	String model;
	Double mileage;
	String vin;

    public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMake() {
		return make;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public Double getMileage() {
		return mileage;
	}
	
	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}
	
	public String getVin() {
		return vin;
	}
	
	public void setVin(String vin) {
		this.vin = vin;
	}
}
