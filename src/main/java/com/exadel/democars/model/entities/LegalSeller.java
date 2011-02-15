package com.exadel.democars.model.entities;

import javax.persistence.Entity;

@Entity
public class LegalSeller extends Seller {
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return companyName;
    }
}
