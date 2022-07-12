package com.qbang.stockpedia.impl;

import com.qbang.stockpedia.config.RedisConfig;
import com.qbang.stockpedia.domain.RedisTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

@Service("RedisService")
public class RedisService {
    public void test() {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        try {
            @SuppressWarnings("unchecked")
            RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) ctx.getBean("redisTemplate");
            // value operation
            ValueOperations<String, Object> values = redisTemplate.opsForValue();
            // set
            values.set("test", new RedisTest("hi"));
            // get
            System.out.println("######## added : " + values.get("test").toString());
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ctx.close();
        }
    }
}
