package com.exadel.democars.model.persistence;

import javax.persistence.Query;
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

    public static List getRangedEntityListByNamedQuery(String queryName, int rangeSize, int selectionNumber) {
        Query rangedQuery = getEntityManagerProvider().getEntityManager().createNamedQuery(queryName);
        rangedQuery.setFirstResult(selectionNumber * rangeSize - rangeSize);
        rangedQuery.setMaxResults(rangeSize);
        return rangedQuery.getResultList();
    }

    public static Integer performCountQuery(String queryName) {
        Query countQuery = getEntityManagerProvider().getEntityManager().createNamedQuery(queryName);
        Long result = (Long) countQuery.getResultList().get(0);
        return result.intValue();
    }
}
