package com.exadel.democars.model.entities;

import javax.persistence.*;

@Entity
@NamedQuery(name = "findAllCars", query = "select c from Car c")
public class Car {
    @Id @GeneratedValue
    Integer id;
    Integer model;
    Double price;
    Integer mileage;
    String vin;
    String condition;
    Integer features;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Integer getFeatures() {
        return features;
    }

    public void setFeatures(Integer features) {
        this.features = features;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
