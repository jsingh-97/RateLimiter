package com.example.RateLimiter.Service;

public interface RateLimiter {
    public boolean isAccessGranted(String userId);
}
