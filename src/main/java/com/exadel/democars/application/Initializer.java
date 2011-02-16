package com.exadel.democars.application;

import com.exadel.democars.beans.car.*;
import com.exadel.democars.model.entities.*;
import com.exadel.democars.model.persistence.DataManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
