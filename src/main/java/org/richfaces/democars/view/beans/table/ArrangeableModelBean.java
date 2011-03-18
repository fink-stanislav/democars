package org.richfaces.democars.view.beans.table;

import org.richfaces.democars.model.entities.Car;
import org.richfaces.democars.view.model.table.ArrangeableModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "arrangeableModelBean")
@SessionScoped
public class ArrangeableModelBean implements Serializable {
    private ArrangeableModel<Car> model;

    public ArrangeableModelBean() {
        model = new ArrangeableModel<Car>(Car.class);
    }

    public ArrangeableModel<Car> getModel() {
        return model;
    }
}
