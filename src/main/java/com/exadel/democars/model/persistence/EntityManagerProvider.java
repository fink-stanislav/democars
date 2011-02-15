package com.exadel.democars.model.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private static EntityManagerProvider instance = new EntityManagerProvider();

    private EntityManagerProvider() {
        Persistence.getPersistenceUtil();
        entityManagerFactory = Persistence.createEntityManagerFactory("demoCars");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static EntityManagerProvider getEntityManagerProvider() {
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void close() {
        entityManagerFactory.close();
        entityManager.close();
    }
}
