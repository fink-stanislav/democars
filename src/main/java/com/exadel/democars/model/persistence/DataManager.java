package com.exadel.democars.model.persistence;

import com.exadel.democars.view.model.expression.PaginationParams;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

import static com.exadel.democars.model.persistence.EntityManagerProvider.getEntityManagerProvider;

public class DataManager {
    private EntityManager entityManager;
    private Integer rowCount = 0;

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

    public List executeQuery(String query, PaginationParams params) {
        Query rangedQuery = entityManager.createQuery(query);

        // ! bad thing
        rowCount = rangedQuery.getResultList().size();

        if (rowCount <= params.getPageSize()) {
            params.setCurrentPage(1);
        }

        rangedQuery.setFirstResult(params.getCurrentPage() * params.getPageSize() - params.getPageSize());
        rangedQuery.setMaxResults(params.getPageSize());
        return rangedQuery.getResultList();
    }

    public Integer getRowCount() {
        return rowCount;
    }
}
