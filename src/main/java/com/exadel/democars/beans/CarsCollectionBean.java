package com.exadel.democars.beans;

import com.exadel.democars.application.DataManager;
import com.exadel.democars.view.PagedListDataModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "carsCollectionBean")
@RequestScoped
public class CarsCollectionBean implements Serializable {
    private DataModel model;

    public DataModel getCars() {
        List pagedList = DataManager.getCarList();
        model = new PagedListDataModel(pagedList, 10, DataManager.getCarList().size());
        return model;
    }
}
