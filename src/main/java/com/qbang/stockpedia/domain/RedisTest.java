package com.qbang.stockpedia.domain;

import java.io.Serializable;

public class RedisTest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    public RedisTest(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id => " + id;
    }
}
