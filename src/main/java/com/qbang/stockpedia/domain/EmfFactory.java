package com.qbang.stockpedia.domain;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfFactory {
    private static final String EMF_NAME = "Stockpedia";
    private static EntityManagerFactory entityManagerFactory
            = Persistence.createEntityManagerFactory(EMF_NAME);

    private EmfFactory() { }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
