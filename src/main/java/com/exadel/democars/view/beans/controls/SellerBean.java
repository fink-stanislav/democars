package com.exadel.democars.view.beans.controls;

import com.exadel.democars.model.entities.Seller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name = "sellerBean")
@RequestScoped
public class SellerBean implements Serializable {
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
