package com.exadel.democars.model.persistence;

import com.exadel.democars.view.model.DefaultDataSource;
import com.exadel.democars.view.model.FilterableDataSource;
import com.exadel.democars.view.model.SortableDataSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    private Query getRangedQuery(DefaultDataSource source) {
        Query rangedQuery = entityManager.createNamedQuery(source.getQueryName());
        rangedQuery.setFirstResult(source.getCurrentPage() * source.getPageSize() - source.getPageSize());
        rangedQuery.setMaxResults(source.getPageSize());
        return rangedQuery;
    }

    public List getRangedList(DefaultDataSource source) {
        return getRangedQuery(source).getResultList();
    }

    public List getRangedSortedList(SortableDataSource source) {
        StringBuilder sb = new StringBuilder();
        sb.append("select c from ")
                .append(source.getTableName())
                .append(" c order by ")
                .append(source.getSortParam()).append(" ")
                .append(source.getSortOrder());
        Query rangedQuery = entityManager.createQuery(sb.toString());
        rangedQuery.setFirstResult(source.getCurrentPage() * source.getPageSize() - source.getPageSize());
        rangedQuery.setMaxResults(source.getPageSize());
        return rangedQuery.getResultList();
    }

    public List getRangedFilteredList(FilterableDataSource source) {
        StringBuilder sb = new StringBuilder();
        sb.append("select c from ")
                .append(source.getTableName())
                .append(" c ");

        Set<Map.Entry<String, String>> entrySet = source.getFilterParams().entrySet();
        if (!entrySet.isEmpty()) {
            sb.append("where ");
        }
        int counter = 0;
        for (Map.Entry<String, String> entry : entrySet) {
            counter++;
            sb.append("upper(c.")
                    .append(entry.getKey())
                    .append(") like '")
                    .append(entry.getValue().toUpperCase())
                    .append("%'");

            if (entrySet.size() > 1 && counter <= entrySet.size() - 1) {
                sb.append(" and ");
            }
        }
        Query rangedQuery = entityManager.createQuery(sb.toString());
        rangedQuery.setFirstResult(source.getCurrentPage() * source.getPageSize() - source.getPageSize());
        rangedQuery.setMaxResults(source.getPageSize());
        return rangedQuery.getResultList();
    }

    public Integer getSingle(String queryName) {
        Query countQuery = entityManager.createNamedQuery(queryName);
        Long result = (Long) countQuery.getSingleResult();
        return result.intValue();
    }
}
