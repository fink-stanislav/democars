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

    public List getList(String queryName) {
        return entityManager.createNamedQuery(queryName).getResultList();
    }

    private Query getRangedQuery(String queryName, int rangeSize, int selectionNumber) {
        Query rangedQuery = entityManager.createNamedQuery(queryName);
        rangedQuery.setFirstResult(selectionNumber * rangeSize - rangeSize);
        rangedQuery.setMaxResults(rangeSize);
        return rangedQuery;
    }

    public List getRangedList(String queryName, int rangeSize, int selectionNumber) {
        return getRangedQuery(queryName, rangeSize, selectionNumber).getResultList();
    }

    public List getRangedSortedList(String tableName, int rangeSize, int selectionNumber, String sortParam, String sortOrder) {
        StringBuilder sb = new StringBuilder();
        sb.append("select c from ").append(tableName)
                .append(" c order by ").append(sortParam).append(" ").append(sortOrder);
        Query rangedQuery = entityManager.createQuery(sb.toString());
        rangedQuery.setFirstResult(selectionNumber * rangeSize - rangeSize);
        rangedQuery.setMaxResults(rangeSize);
        return rangedQuery.getResultList();
    }

    public List getRangedFilteredList(String queryName, int rangeSize, int selectionNumber, String column, String expression) {
        String q = "select c from Car c where upper(c." + column + " ) like '" + expression.toUpperCase() + "%'";
        return entityManager.createQuery(q).getResultList();
    }

    public Integer getSingle(String queryName) {
        Query countQuery = entityManager.createNamedQuery(queryName);
        Long result = (Long) countQuery.getSingleResult();
        return result.intValue();
    }
}
