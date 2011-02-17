package com.exadel.democars.model.persistence;

import com.exadel.democars.beans.car.*;
import com.exadel.democars.model.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

import static com.exadel.democars.model.persistence.EntityManagerProvider.getEntityManagerProvider;

public class DataManager {
    public void persistEntity(Object entity) {
    }

    public void removeEntity(Object entity) {
    }

    public void updateEntity(Object entity) {
    }

    public static List getEntityListByNamedQuery(String queryName) {
        return getEntityManagerProvider().getEntityManager().createNamedQuery(queryName).getResultList();
    }
}
