package com.exadel.democars.view.beans;

import com.exadel.democars.application.DataManager;
import com.exadel.democars.view.model.PagedListDataModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "carsCollectionBean")
@RequestScoped
public class CarsCollectionBean implements Serializable {
    public DataModel getCars() {
        DataManager dataManager = new DataManager();
        List pagedList = dataManager.getCarList();
        return new PagedListDataModel(pagedList, 10, dataManager.getCarList().size());
    }
}
