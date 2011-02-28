package com.exadel.democars.view.beans.table;

import com.exadel.democars.beans.car.*;
import com.exadel.democars.model.entities.Car;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "carCreationBean")
@SessionScoped
public class CarCreationBean {
    private Car car;
    private Double price;
    private Integer mileage;
    private String vin;
    private Condition condition;
    private String model;
    private String make;
    private BodyType bodyType;
    private Fuel fuel;
    private Engine engine;
    private Transmission transmission;
    private String exteriorColor;
    private String interiorColor;

    public CarCreationBean() {
        car = new Car();
    }

    public Double getPrice() {
        return car.getPrice();
    }

    public void setPrice(Double price) {
        car.setPrice(price);
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public void createCar() {
    }
}
