package com.example.RateLimiter.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("127.0.0.1");
        redisStandaloneConfiguration.setPort(6379);
        JedisConnectionFactory jedisConnectionFactory=new JedisConnectionFactory(redisStandaloneConfiguration);
        return jedisConnectionFactory;
    }
    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
