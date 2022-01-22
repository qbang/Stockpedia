package com.qbang.stockpedia.impl;

import com.qbang.stockpedia.domain.Stock;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerService {

    private final RequestStockService requestStockService;
    private final ProcessStockService processStockService;
    private final TierService tierService;

    //1시간 마다 실행
    @Scheduled(cron="0 0 0/1 * * *")
    @Async
    public void batchForStock() throws IOException {
        List<Stock> stock = processStockService.searchTodayStock();

        if (stock == null || stock.size() == 0) {
            HashSet<String> codeSet = requestStockService.getItemCode();
            JSONArray ret = requestStockService.getItemInfo(codeSet);
            // 종목명이랑 가격만 빼주고 DB에 넣어주기
            HashMap<String, Integer> map = processStockService.parseItemInfo(ret);
            processStockService.registerStock(map);
        }
    }

    //매주 월요일 0시에 실행
    @Scheduled(cron="0 0 0 * * MON")
    @Async
    public void batchForTier() {
        tierService.updateTier();
    }
}
