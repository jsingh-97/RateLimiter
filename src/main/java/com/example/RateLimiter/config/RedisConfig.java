package com.example.RateLimiter.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

@Configuration
public class RedisConfig {
    class IntRedisSerializer implements RedisSerializer<Integer>{

        @Override
        public byte[] serialize(Integer i) throws SerializationException {
            return i.toString().getBytes();
        }

        @Override
        public Integer deserialize(byte[] bytes) throws SerializationException {
            if(bytes==null) return null;
            else return Integer.parseInt(new String(bytes));
        }
    }
    @Bean
    public  LettuceConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost",6379));
    }
    @Bean
    public  RedisTemplate<String,Integer> redisTemplate(){
        RedisTemplate<String,Integer> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(new IntRedisSerializer());
        return redisTemplate;
    }

}
