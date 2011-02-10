package com.exadel.democars.beans;

import com.exadel.democars.application.DataManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import java.io.Serializable;

@ManagedBean(name = "carsCollectionBean")
@RequestScoped
public class CarsCollectionBean implements Serializable {
    private DataModel<Car> model = new ArrayDataModel<Car>(DataManager.getCarList());

    public DataModel<Car> getCars() {
        return model;
    }
}
