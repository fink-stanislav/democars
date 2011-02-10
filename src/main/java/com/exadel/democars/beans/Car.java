package com.exadel.democars.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name = "car")
@RequestScoped
public class Car implements Serializable {
    Integer id;
    String make;
    String model;
    Float price;
    Integer mileage;
    String vin;
    Condition condition;

    public Car() {};

    public enum Condition {
        Used, New
    }

    public Car(String make, String model, Float price, Integer mileage, String vin, Condition condition) {
        this.make = make;
        this.model = model;
        this.price = price;
        this.mileage = mileage;
        this.vin = vin;
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
