package org.richfaces.democars.model.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.richfaces.democars.application.PropertyManager.getPropertyManager;

/**
 * Provides {@code EntityManager} for performing CRUD operations.
 *
 * @author S. Fink
 */
public class EntityManagerProvider {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private static EntityManagerProvider instance = new EntityManagerProvider();

    /**
     * Creates {@code EntityManager} from specified persistence unit.
     */
    private EntityManagerProvider() {
        entityManagerFactory = Persistence.createEntityManagerFactory(
                getPropertyManager().getProperty("persistence.unit")
        );
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static EntityManagerProvider getEntityManagerProvider() {
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void close() {
        entityManagerFactory.close();
        entityManager.close();
    }
}
