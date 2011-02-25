package com.exadel.democars.view.beans.table;

import com.exadel.democars.model.entities.Car;
import com.exadel.democars.model.entities.Features;
import com.exadel.democars.model.entities.Seller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "carCreationBean")
@RequestScoped
public class CarCreationBean {
    private Car car;
    private Features features;
    private Seller seller;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
