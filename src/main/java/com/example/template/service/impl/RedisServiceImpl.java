package com.example.template.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void setValue(String key, String value, Long expiredTime) {
        redisTemplate.opsForValue().set(key, value, expiredTime, TimeUnit.SECONDS);
    }

    public String getValue(String key) {
        String s = redisTemplate.opsForValue().get(key);
        return s;
    }

    public boolean deleteKey(String key) {
        return redisTemplate.delete(key);
    }

}
