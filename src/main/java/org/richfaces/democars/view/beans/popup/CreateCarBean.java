package org.richfaces.democars.view.beans.popup;

import org.richfaces.democars.model.persistence.DataManager;
import org.richfaces.democars.model.entities.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "createCarBean")
@SessionScoped
public class CreateCarBean {
    private DataManager dataManager;
    private Car car;
    private Features features;
    private Address address;
    private Seller seller;
    private IndividualSeller individualSeller;
    private LegalSeller legalSeller;
    private Model model;
    private String sellerType;
    private Boolean ajaxRendered;

    public Boolean getAjaxRendered() {
        return ajaxRendered;
    }

    public void setAjaxRendered(Boolean ajaxRendered) {
        this.ajaxRendered = ajaxRendered;
    }

    public CreateCarBean() {
        clear();
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
        seller.setDtype(seller.getClass().getName());
        car.setSeller(seller);
    }

    public void clear() {
        initCar();
        initSeller();
    }

    public void createCar() {
        determineSeller();
        dataManager.persistEntities(features, address, model, seller, car);
        clear();
    }

    private void determineSeller() {
        if (sellerType.equals("legalSeller")) {
            seller = legalSeller;
            seller.setDtype(seller.getClass().getName());
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
