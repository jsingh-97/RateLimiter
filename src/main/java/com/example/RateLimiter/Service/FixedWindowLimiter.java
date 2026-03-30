package com.example.RateLimiter.Service;

import com.example.RateLimiter.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service("fixedWindow")
public class FixedWindowLimiter implements RateLimiter {
    static final int LIMIT = 3;
    @Autowired
    RedisConfig redisConfig;

    @Override
    public boolean isAccessGranted(String userId) {
        Integer val = redisConfig.redisTemplate().opsForValue().get(userId);
        if (val == null) {
            redisConfig.redisTemplate().opsForValue().set(userId, 1, Duration.ofSeconds(120));
            return true;
        } else {
            if (val < LIMIT) {
                redisConfig.redisTemplate().opsForValue().increment(userId);
                return true;
            } else {
                return false;
            }
        }
    }
}
