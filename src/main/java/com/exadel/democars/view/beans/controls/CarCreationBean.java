package com.exadel.democars.view.beans.controls;

import com.exadel.democars.model.entities.*;
import com.exadel.democars.model.persistence.DataManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "carCreationBean")
@SessionScoped
public class CarCreationBean {
    private DataManager dataManager;
    private Car car;
    private Features features;
    private Address address;
    private Seller seller;
    private IndividualSeller individualSeller;
    private LegalSeller legalSeller;
    private Model model;
    private String sellerType;

    public CarCreationBean() {
        initCar();
        initSeller();
        dataManager = new DataManager();
        sellerType = "individualSeller";
    }

    private void initCar() {
        car = new Car();
        features = new Features();
        model = new Model();
        car.setModel(model);
        car.setFeatures(features);
    }

    private void initSeller() {
        address = new Address();
        individualSeller = new IndividualSeller();
        legalSeller = new LegalSeller();
        seller = individualSeller;
        seller.setAddress(address);
        car.setSeller(seller);
    }

    public void createCar() {
        determineSeller();
        dataManager.persistEntities(features, address, model, seller, car);
        initCar();
        initSeller();
    }

    private void determineSeller() {
        if (sellerType.equals("legalSeller")) {
            seller = legalSeller;
            seller.setAddress(address);
            car.setSeller(seller);
        }
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public String getCompanyName() {
        return legalSeller.getCompanyName();
    }

    public void setCompanyName(String companyName) {
        legalSeller.setCompanyName(companyName);
    }

    public String getLastName() {
        return individualSeller.getLastname();
    }

    public void setLastName(String lastName) {
        individualSeller.setLastname(lastName);
    }

    public String getFirstName() {
        return individualSeller.getFirstname();
    }

    public void setFirstName(String firstName) {
        individualSeller.setFirstname(firstName);
    }
}