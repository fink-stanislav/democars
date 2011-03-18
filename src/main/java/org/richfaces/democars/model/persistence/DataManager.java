package org.richfaces.democars.model.persistence;

import org.richfaces.democars.model.params.PaginationParams;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.richfaces.democars.model.persistence.EntityManagerProvider.getEntityManagerProvider;

/**
 * Implements CRUD operations for entities.
 *
 * @author S. Fink
 */
public class DataManager<T> {
    private static Boolean populated = false;
    private EntityManager entityManager;
    private Class<T> entityClass;

    public DataManager(Class<T> entityClass) {
        entityManager = getEntityManagerProvider().getEntityManager();
        this.entityClass = entityClass;
        if (!populated) {
            try {
                new DataBasePopulator().populate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeEntity(T entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(entity);
        entityTransaction.commit();
    }

    public void updateEntity(T entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(entity);
        entityTransaction.commit();
    }

    public void persistEntities(Object... entities) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        for (Object entity : entities) {
            entityManager.persist(entity);
        }
        entityTransaction.commit();
    }

    public Integer executeQuery(String query) {
        TypedQuery<Long> singleResultQuery = entityManager.createQuery(query, Long.class);
        return singleResultQuery.getSingleResult().intValue();
    }

    public T getEntityById(Object id, Class<T> entityClass) {
        return entityManager.find(entityClass, id);
    }

    /**
     * Executes JPQL query from string. Retrieves specified number of results.
     *
     * @param query  JPQL query string
     * @param params contains range for results
     * @return List of entities
     */
    public List<T> executeQuery(String query, PaginationParams params) {
        TypedQuery<T> rangedQuery = entityManager.createQuery(query, entityClass);
        rangedQuery.setFirstResult(params.getCurrentPage() * params.getPageSize() - params.getPageSize());
        rangedQuery.setMaxResults(params.getPageSize());
        return rangedQuery.getResultList();
    }

    public List <T>executeQuery(String query, Integer firstRow, Integer rowCount) {
        TypedQuery<T> rangedQuery = entityManager.createQuery(query, entityClass);
        rangedQuery.setFirstResult(firstRow);
        rangedQuery.setMaxResults(rowCount);
        return rangedQuery.getResultList();
    }
}
