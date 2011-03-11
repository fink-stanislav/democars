package org.richfaces.democars.model.entities;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class IndividualSeller extends Seller implements Serializable {
    private String firstname;
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
