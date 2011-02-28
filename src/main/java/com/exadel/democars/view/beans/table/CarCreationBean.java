package com.exadel.democars.view.beans.table;

import com.exadel.democars.beans.car.*;
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
    private Model carModel;
    private String sellerType;

    public CarCreationBean() {
        car = new Car();
        features = new Features();
        address = new Address();
        individualSeller = new IndividualSeller();
        legalSeller = new LegalSeller();
        carModel = new Model();
        dataManager = new DataManager();
    }

    private void reinit() {
        car = new Car();
        features = new Features();
        address = new Address();
        individualSeller = new IndividualSeller();
        legalSeller = new LegalSeller();
        carModel = new Model();
    }

    private void createSeller() {
        if (sellerType.equals("individualSeller")) {
            seller = individualSeller;
        } else if (sellerType.equals("legalSeller")) {
            seller = legalSeller;
        }
    }

    public void createCar() {
        createSeller();
        car.setModel(carModel);
        car.setFeatures(features);
        seller.setAddress(address);
        car.setSeller(seller);
        dataManager.persistEntity(features);
        dataManager.persistEntity(address);
        dataManager.persistEntity(carModel);
        dataManager.persistEntity(seller);
        dataManager.persistEntity(car);
        reinit();
    }

    public Double getPrice() {
        return car.getPrice();
    }

    public void setPrice(Double price) {
        car.setPrice(price);
    }

    public Integer getMileage() {
        return car.getMileage();
    }

    public void setMileage(Integer mileage) {
        car.setMileage(mileage);
    }

    public String getVin() {
        return car.getVin();
    }

    public void setVin(String vin) {
        car.setVin(vin);
    }

    public Condition getCondition() {
        return car.getCondition();
    }

    public void setCondition(Condition condition) {
        car.setCondition(condition);
    }

    public String getModel() {
        return carModel.getModel();
    }

    public void setModel(String model) {
        carModel.setModel(model);
    }

    public String getMake() {
        return carModel.getMake();
    }

    public void setMake(String make) {
        carModel.setMake(make);
    }

    public String getInteriorColor() {
        return features.getInteriorColor();
    }

    public void setInteriorColor(String interiorColor) {
        features.setInteriorColor(interiorColor);
    }

    public String getExteriorColor() {
        return features.getExteriorColor();
    }

    public void setExteriorColor(String exteriorColor) {
        features.setExteriorColor(exteriorColor);
    }

    public Transmission getTransmission() {
        return features.getTransmission();
    }

    public void setTransmission(Transmission transmission) {
        features.setTransmission(transmission);
    }

    public Engine getEngine() {
        return features.getEngine();
    }

    public void setEngine(Engine engine) {
        features.setEngine(engine);
    }

    public Fuel getFuel() {
        return features.getFuel();
    }

    public void setFuel(Fuel fuel) {
        features.setFuel(fuel);
    }

    public BodyType getBodyType() {
        return features.getBodyType();
    }

    public void setBodyType(BodyType bodyType) {
        features.setBodyType(bodyType);
    }

    public String getStreet() {
        return address.getStreet();
    }

    public void setStreet(String street) {
        address.setStreet(street);
    }

    public String getCity() {
        return address.getCity();
    }

    public void setCity(String city) {
        address.setCity(city);
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
