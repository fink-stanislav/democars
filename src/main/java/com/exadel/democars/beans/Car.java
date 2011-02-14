package com.exadel.democars.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name = "car")
@RequestScoped
public class Car implements Serializable {
    Integer id;
    Model model;
    Float price;
    Integer mileage;
    String vin;
    Condition condition;
    Features features;

    public Car() {}

    public enum Condition {
        Used, New
    }

    public Car(String make, String model, Float price, Integer mileage, String vin, Condition condition) {
        this.model = new Model();
        this.model.setMake(make);
        this.model.setModel(model);
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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
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

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }
}
