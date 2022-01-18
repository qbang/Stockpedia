package com.qbang.stockpedia.domain;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfFactory {
    private static final EntityManagerFactory entityManagerFactory
            = Persistence.createEntityManagerFactory("Stockpedia");;

    private EmfFactory() { }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
