package com.bm.server.webservice.guice.modules;

import com.google.inject.AbstractModule;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit");
        bind(EntityManager.class).toInstance(factory.createEntityManager());
    }
}
