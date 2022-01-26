package com.qbang.stockpedia.domain;

import java.io.Serializable;
import java.util.List;

public class StockRedis implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Stock> list;

    public StockRedis(List<Stock> list) {
        this.list = list;
    }
}
