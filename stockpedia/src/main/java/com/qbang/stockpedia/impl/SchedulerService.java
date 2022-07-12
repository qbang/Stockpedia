package com.qbang.stockpedia.impl;

import com.qbang.stockpedia.config.RedisConfig;
import com.qbang.stockpedia.domain.Stock;
import com.qbang.stockpedia.dto.StockRedis;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerService {
    private final RequestStockService requestStockService;
    private final ProcessStockService processStockService;
    private final TierService tierService;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
    @SuppressWarnings("unchecked")
    private RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) ctx.getBean("redisTemplate");

    //1시간 마다 실행
    @Scheduled(cron="0 0 0/1 * * *")
    @Async
    public void batchForStock() throws IOException {
//        List<Stock> stock = processStockService.searchTodayStock();
//        if (stock == null || stock.size() == 0) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        if (values.get("2021-06-04") == null) {
//            HashSet<String> codeSet = requestStockService.getItemCode();
//            JSONArray ret = requestStockService.getItemInfo(codeSet);
//             종목명이랑 가격만 빼주고 DB에 넣어주기
//            HashMap<String, Integer> map = processStockService.parseItemInfo(ret);
//            processStockService.registerStock(map);
            List<Stock> list = processStockService.searchPastStock();
            values.set("2021-06-04", new StockRedis(list));
        }
    }

    //매주 월요일 0시에 실행
    @Scheduled(cron="0 0 0 * * MON")
    @Async
    public void batchForTier() {
        tierService.updateTier();
    }
}
