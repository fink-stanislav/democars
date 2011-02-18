package com.exadel.democars.view.beans;

import com.exadel.democars.model.entities.Seller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "sellerInfoBean")
@RequestScoped
public class SellerInfoBean {
    private Seller seller;

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String outcome() {
        return "";
    }
}
