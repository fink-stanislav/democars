package com.exadel.democars.model.entities;

import com.exadel.democars.beans.car.Condition;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "findAllCars", query = "select c from Car c"),
        @NamedQuery(name = "countCars", query = "select count(c) from Car c")
})
public class Car {
    @Id
    @GeneratedValue
    private Integer id;
    private Double price;
    private Integer mileage;
    private String vin;
    @Enumerated(EnumType.STRING)
    private Condition condition;

    @ManyToOne
    @JoinColumn(name = "seller")
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "model")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "features")
    private Features features;

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
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

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }
}
