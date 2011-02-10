package com.exadel.democars.beans;

import com.exadel.democars.application.DataManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.FileNotFoundException;
import java.io.Serializable;

@ManagedBean(name = "carsCollectionBean")
@RequestScoped
public class CarsCollectionBean implements Serializable {

    public Car[] getCars() {
        return DataManager.getCarList();
    }
}
