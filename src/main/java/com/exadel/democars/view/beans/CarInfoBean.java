package com.exadel.democars.view.beans;

import com.exadel.democars.model.entities.Car;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "carInfoBean")
@RequestScoped
public class CarInfoBean {
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
