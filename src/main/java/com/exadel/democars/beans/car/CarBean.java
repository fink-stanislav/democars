package com.exadel.democars.beans.car;

import com.exadel.democars.model.entities.LegalSeller;
import com.exadel.democars.model.entities.Seller;

public class CarBean {
    private Integer id;
    private ModelBean model;
    private Double price;
    private Integer mileage;
    private String vin;
    private Condition condition;
    private FeaturesBean features;
    private Seller seller;

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

    public ModelBean getModel() {
        return model;
    }

    public void setModel(ModelBean model) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public FeaturesBean getFeatures() {
        return features;
    }

    public void setFeatures(FeaturesBean features) {
        this.features = features;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
