package com.qbang.stockpedia.dto;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Component
public class EmfFactory {
    private static final String EMF_NAME = "Stockpedia";
    private static EntityManagerFactory entityManagerFactory;

    private EmfFactory() { }

    public static synchronized EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(EMF_NAME);
        }
        return entityManagerFactory;
    }
}
