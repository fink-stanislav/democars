package com.exadel.democars.model.persistence;

import com.exadel.democars.util.JpqlExpressionBuilder;
import com.exadel.democars.view.model.datasource.JpqlDataSource;

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

    public List executeQuery(JpqlExpressionBuilder builder, JpqlDataSource source) {
        Query rangedQuery = entityManager.createQuery(builder.getExpression());

        // ! bad thing
        rowCount = rangedQuery.getResultList().size();

        rangedQuery.setFirstResult(source.getCurrentPage() * source.getPageSize() - source.getPageSize());
        rangedQuery.setMaxResults(source.getPageSize());
        return rangedQuery.getResultList();
    }

    public Integer getRowCount() {
        return rowCount;
    }
}
