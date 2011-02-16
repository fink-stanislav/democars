package com.exadel.democars.application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static com.exadel.democars.model.persistence.EntityManagerProvider.getEntityManagerProvider;

public class Initializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        getEntityManagerProvider();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        getEntityManagerProvider().close();
    }
}
