package com.exadel.democars.application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationInitializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("demoCars");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.createNamedQuery("findAllCars").getResultList();

        entityManager.close();
        entityManagerFactory.close();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
