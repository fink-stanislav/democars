package com.exadel.democars.model.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

import static com.exadel.democars.model.persistence.EntityManagerProvider.getEntityManagerProvider;

public class DataManager {
    private EntityManager entityManager;

    public DataManager() {
        entityManager = getEntityManagerProvider().getEntityManager();
    }

    public void persistEntity(Object entity) {
    }

    public void detach(Object entity) {
        if (entityManager.contains(entity)) {
            entityManager.detach(entity);
        }
        entityManager.detach(entity);
    }

    public void removeEntity(Object entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(entity);
        entityTransaction.commit();
    }

    public <T> void updateEntity(T entity, Object primaryKey) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        T t = (T) entityManager.find(entity.getClass(), primaryKey);
        t = entity;
        entityTransaction.commit();
    }

    public List getList(String queryName) {
        return entityManager.createNamedQuery(queryName).getResultList();
    }

    public <T> List<T> getRangedList(String queryName, int rangeSize, int selectionNumber) {
        Query rangedQuery = entityManager.createNamedQuery(queryName);
        rangedQuery.setFirstResult(selectionNumber * rangeSize - rangeSize);
        rangedQuery.setMaxResults(rangeSize);
        return rangedQuery.getResultList();
    }

    public <T> List<T> getRangedSortedList(String queryName, int rangeSize, int selectionNumber, String sortParam) {
        Query rangedQuery = entityManager.createNamedQuery(queryName);
        rangedQuery.setFirstResult(selectionNumber * rangeSize - rangeSize);
        rangedQuery.setMaxResults(rangeSize);
        //rangedQuery.setParameter("sortParam", sortParam);
        return rangedQuery.getResultList();
    }

    public Integer getSingle(String queryName) {
        Query countQuery = entityManager.createNamedQuery(queryName);
        Long result = (Long) countQuery.getSingleResult();
        return result.intValue();
    }
}
