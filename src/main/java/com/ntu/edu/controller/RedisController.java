package com.ntu.edu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntu.edu.Application;
import com.ntu.edu.model.RedisBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Redis 控制，具体操作详见 {@link com.ntu.test.redis.RedisTest}
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/test")
    private void test(int type){
        switch (type){
            case 0:
                testValue();
                break;
            case 1:
                testGeo();
                break;
        }
    }

    /**
     * 测试 传入单个对象（都通过 JackSon 转成 Json 存储）
     */
    @Test
    public void testValue(){

//        redisTemplate.opsForValue().set("value", "测试值加入");

//        redisTemplate.opsForValue().getAndSet("value", "替换测试值");
    }

    /**
     * redis 集群的使用，暂时不需要
     */
    private void testCluster(){
        redisTemplate.opsForCluster();
    }

    /**
     * redis geo(地理位置)使用
     */
    private void testGeo(){
        redisTemplate.opsForGeo().add("beijing", new Point(116.405285,39.904989),"北京");

        redisTemplate.opsForGeo().remove("beijing");
    }

}
