package com.exadel.democars.application;

import java.util.List;

import static com.exadel.democars.model.persistence.EntityManagerProvider.getEntityManagerProvider;

public class DataManager {
    public List getCarList() {
        return getEntityManagerProvider().getEntityManager().createNamedQuery("findAllCars").getResultList();
    }
}
