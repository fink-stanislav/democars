package com.exadel.democars.view.beans.table;

import com.exadel.democars.beans.car.*;
import com.exadel.democars.model.entities.Car;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "carCreationBean")
@SessionScoped
public class CarCreationBean {
    private String property;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void createCar() {
    }
}
