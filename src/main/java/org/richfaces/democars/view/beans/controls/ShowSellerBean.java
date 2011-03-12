package org.richfaces.democars.view.beans.controls;

import org.richfaces.democars.model.entities.Seller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name = "showSellerBean")
@RequestScoped
public class ShowSellerBean implements Serializable {
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
