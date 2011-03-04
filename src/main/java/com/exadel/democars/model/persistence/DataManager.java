package com.exadel.democars.model.persistence;

import com.exadel.democars.view.model.expression.PaginationParams;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.exadel.democars.model.persistence.EntityManagerProvider.getEntityManagerProvider;

/**
 * Implements CRUD operations for entities.
 *
 * @author S. Fink
 */
public class DataManager {
    private EntityManager entityManager;

    public DataManager() {
        entityManager = getEntityManagerProvider().getEntityManager();
    }

    public <T> void removeEntity(T entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(entity);
        entityTransaction.commit();
    }

    public <T> void updateEntity(T entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(entity);
        entityTransaction.commit();
    }

    public <T> void persistEntities(T... entities) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        for (T entity : entities) {
            entityManager.persist(entity);
        }
        entityTransaction.commit();
    }

    public Integer executeQuery(String query) {
        TypedQuery<Long> singleResultQuery = entityManager.createQuery(query, Long.class);
        return singleResultQuery.getSingleResult().intValue();
    }

    /**
     * Executes JPQL query from string. Retrieves specified number of results.
     *
     * @param query  JPQL query string
     * @param params contains range for results
     * @return List of entities
     */
    public List executeQuery(String query, PaginationParams params) {
        Query rangedQuery = entityManager.createQuery(query);
        rangedQuery.setFirstResult(params.getCurrentPage() * params.getPageSize() - params.getPageSize());
        rangedQuery.setMaxResults(params.getPageSize());
        return rangedQuery.getResultList();
    }
}
