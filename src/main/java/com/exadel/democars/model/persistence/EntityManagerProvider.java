package com.exadel.democars.model.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Provides {@code EntityManager} for performing CRUD operations.
 *
 * @author S. Fink
 */
public class EntityManagerProvider {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    private static EntityManagerProvider instance = new EntityManagerProvider();

    /**
     * Creates {@code EntityManager} from specified persistence unit.
     */
    private EntityManagerProvider() {
        entityManagerFactory = Persistence.createEntityManagerFactory("demoCars");
        entityManager = entityManagerFactory.createEntityManager();
        criteriaBuilder = entityManagerFactory.getCriteriaBuilder();
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

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public void close() {
        entityManagerFactory.close();
        entityManager.close();
    }
}
