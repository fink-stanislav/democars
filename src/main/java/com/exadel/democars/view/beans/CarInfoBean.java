package com.exadel.democars.view.beans;

import com.exadel.democars.model.entities.Car;
import com.exadel.democars.model.persistence.DataManager;
import com.exadel.democars.model.persistence.EntityManagerProvider;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "carInfoBean")
@SessionScoped
public class CarInfoBean implements Serializable {
    private Car car;
    private DataManager dataManager;

    public CarInfoBean() {
        dataManager = new DataManager();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void deleteCar() {
        dataManager.removeEntity(car);
    }

    public void updateCar() {
        dataManager.updateEntity(car, car.getId());
    }
}