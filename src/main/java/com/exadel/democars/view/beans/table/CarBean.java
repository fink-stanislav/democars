package com.exadel.democars.view.beans.table;

import com.exadel.democars.model.entities.Car;
import com.exadel.democars.model.persistence.DataManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "carBean")
@SessionScoped
public class CarBean implements Serializable {
    private Car car;
    private DataManager dataManager;

    public CarBean() {
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
        dataManager.updateEntity(car);
    }
}
