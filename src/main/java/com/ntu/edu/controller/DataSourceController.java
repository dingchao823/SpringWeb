package com.ntu.edu.controller;

import com.ntu.edu.config.ConstantConfig;
import com.ntu.edu.config.datasource.DataSource;
import com.ntu.edu.mapper.UserMapper;
import com.ntu.edu.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/datasource")
public class DataSourceController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/first")
    @DataSource(name = ConstantConfig.DataSourceName.FIRST)
    public List<UserEntity> testFirstDataSource(){
        return userMapper.query();
    }

    @RequestMapping("/second")
    @DataSource(name = ConstantConfig.DataSourceName.SECOND)
    public List<UserEntity> testSecondDataSource(){
        return userMapper.query();
    }

}
