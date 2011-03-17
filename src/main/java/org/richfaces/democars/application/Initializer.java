package org.richfaces.democars.application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.io.IOException;

import static org.richfaces.democars.model.persistence.EntityManagerProvider.getEntityManagerProvider;

public class Initializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        getEntityManagerProvider();
        try {
            new DataBasePopulator().build();
        } catch (Exception e) {
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        getEntityManagerProvider().close();
    }
}
