package org.richfaces.democars.view.beans.popup;

import org.richfaces.democars.model.entities.Car;
import org.richfaces.democars.model.persistence.DataManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "editCarBean")
@SessionScoped
public class EditCarBean implements Serializable {
    private Car car;
    private DataManager dataManager;

    public EditCarBean() {
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
