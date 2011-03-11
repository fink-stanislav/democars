package org.richfaces.democars.model.entities;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class LegalSeller extends Seller implements Serializable {
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
