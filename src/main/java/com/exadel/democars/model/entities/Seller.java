package com.exadel.democars.model.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Seller implements Serializable {
    @Id
    @GeneratedValue
    protected Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address")
    protected Address address;

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public abstract String toString();
}
