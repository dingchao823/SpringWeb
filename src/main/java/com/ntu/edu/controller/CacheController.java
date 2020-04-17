package com.ntu.edu.controller;

import com.ntu.edu.mapper.UserMapper;
import com.ntu.edu.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 测试使用 Redis 作为内存缓存，涉及 CRUD 操作
 *
 * @author 56392
 */
@RestController
@CacheConfig(cacheNames = "redisTest")
@RequestMapping("/cacheTest")
@Slf4j
public class CacheController {

    @Autowired
    UserMapper userMapper;

    private int count = 0;

    @RequestMapping("/queryUsers")
    public List<UserEntity> queryAll(){
        return userMapper.selectList(null);
    }

    @RequestMapping("/insertUser")
    public String insertOneUser(){
        for (int i = 0; i < 20; i++){
            UserEntity userEntity = new UserEntity();
            userEntity.setName("第" + count + "次," + "第" + i + "个用户");
            userEntity.setAge(i);
            userMapper.insert(userEntity);
        }
        count++;
        return "插入 20 条数据成功";
    }

    /**
     * 测试获取缓存数据
     *
     *
     * @return /
     */
    @Cacheable(key = "#root.methodName+'['+#id+']'")
    @RequestMapping("/getCacheData")
    public UserEntity testGetCacheData(int id){
        log.debug("getting data from mysql");
        return userMapper.selectById(id);
    }

}
