package com.ntu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ntu.edu.entity.UserEntity;
import com.ntu.edu.mapper.UserMapper;
import com.ntu.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(UserEntity userEntity) {
        return userMapper.insert(userEntity);
    }

    @Override
    public List<UserEntity> query() {
        return userMapper.selectList(null);
    }

    @Override
    public int insertFromXml(UserEntity userEntity) {
        return userMapper.insertFromXml(userEntity);
    }
}
