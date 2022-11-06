package com.example.RateLimiter.controller;

import com.example.RateLimiter.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class HomePageController {
    static private int LIMIT=3;
    @Autowired
    RedisConfig redisConfig;
    @RequestMapping(value = "/access",method = RequestMethod.GET)
    public boolean getRequestAccess(@RequestParam(name="userId") String userId){
        String key = userId;
        Integer val=redisConfig.redisTemplate().opsForValue().get(userId);
        if(val==null){
            redisConfig.redisTemplate().opsForValue().set(userId,1,Duration.ofSeconds(120));
            return true;
        }
        else{
            if(val<LIMIT){
                redisConfig.redisTemplate().opsForValue().increment(userId);
                return true;
            }else{
                return false;
            }
        }
    }
}
