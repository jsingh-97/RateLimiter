package com.example.RateLimiter.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service("slidingWindow")
public class SlidingWindowLimiter implements RateLimiter {
    private static final int LIMIT = 1;
    private static final long WINDOW_SECONDS = 5;

    @Autowired
    @Qualifier("redisStringTemplate")
    RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean isAccessGranted(String userId) {
        String key = "slidingLog:" + userId;
        long now = System.currentTimeMillis();
        long windowStart = now - Duration.ofSeconds(WINDOW_SECONDS).toMillis();
        // Step 1: Remove all timestamps older than the window
        redisTemplate.opsForZSet()
                .removeRangeByScore(key, 0, windowStart);
        // Step 2: Count remaining timestamps in window
        Long count = redisTemplate.opsForZSet().zCard(key);
        if (count == null || count < LIMIT) {
            redisTemplate.opsForZSet()
                    .add(key, String.valueOf(now), now);  // score
            redisTemplate.expire(key, Duration.ofSeconds(WINDOW_SECONDS));
            return true;
        }
        return false;
    }
}
