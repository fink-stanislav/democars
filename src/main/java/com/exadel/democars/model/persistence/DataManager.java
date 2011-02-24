package com.exadel.democars.model.persistence;

import com.exadel.democars.util.JpqlExpressionBuilder;
import com.exadel.democars.view.model.datasource.DefaultDataSource;
import com.exadel.democars.view.model.datasource.FilterableDataSource;
import com.exadel.democars.view.model.datasource.JpqlDataSource;
import com.exadel.democars.view.model.datasource.SortableDataSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    private Query buildRangedQuery(JpqlExpressionBuilder builder, JpqlDataSource source) {
        Query rangedQuery = entityManager.createQuery(builder.getExpression());

        // ! bad thing
        rowCount = rangedQuery.getResultList().size();

        rangedQuery.setFirstResult(source.getCurrentPage() * source.getPageSize() - source.getPageSize());
        rangedQuery.setMaxResults(source.getPageSize());
        return rangedQuery;
    }

    public List getRangedList(DefaultDataSource source) {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(source);
        builder.buildSelectExpression();
        return buildRangedQuery(builder, source).getResultList();
    }

    public List getRangedSortedList(SortableDataSource source) {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(source);
        builder.buildSelectExpression();
        builder.buildOrderByExpression(source.getSortParam(), source.getSortOrder());
        return buildRangedQuery(builder, source).getResultList();
    }

    public List getRangedFilteredList(FilterableDataSource source) {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(source);
        builder.buildSelectExpression();

        Set<Map.Entry<String, Object>> entrySet = source.getFilterParams().entrySet();
        if (!entrySet.isEmpty()) {
            builder.addWhere();
        }
        int counter = 0;
        for (Map.Entry<String, Object> entry : entrySet) {
            counter++;
            if (entry.getValue() instanceof String) {
                builder.buildLikeExpression(entry.getKey(), entry.getValue());
                if (entrySet.size() > 1 && counter <= entrySet.size() - 1) {
                    builder.addAnd();
                }
            } else if (entry.getValue() instanceof Number) {
                builder.buildComparsionExpression(entry.getKey(), entry.getValue(), "<=");
                if (entrySet.size() > 1 && counter <= entrySet.size() - 1) {
                    builder.addAnd();
                }
            }
        }
        return buildRangedQuery(builder, source).getResultList();
    }

    public Integer getRowCount() {
        return rowCount;
    }
}
