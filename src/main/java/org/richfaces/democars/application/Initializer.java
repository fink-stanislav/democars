package org.richfaces.democars.application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.io.IOException;

import static org.richfaces.democars.model.persistence.EntityManagerProvider.getEntityManagerProvider;

/**
 * Initializes persistence provider.
 *
 * @author S. Fink
 */
public class Initializer implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        getEntityManagerProvider();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        getEntityManagerProvider().close();
    }
}
