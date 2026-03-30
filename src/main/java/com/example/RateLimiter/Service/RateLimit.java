package com.example.RateLimiter.Service;

import org.springframework.stereotype.Service;

@Service
public interface RateLimit {
    public boolean isAccessGranted(String userId);
}
