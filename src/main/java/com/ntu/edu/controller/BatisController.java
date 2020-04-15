package com.ntu.edu.controller;

import com.ntu.edu.model.UserEntity;
import com.ntu.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BatisController {

    @Autowired
    private UserService userService;

    @RequestMapping("/insert")
    public String insert(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("小明");
        userEntity.setAge(28);
        userService.insert(userEntity);
        return "insert success";
    }

    @RequestMapping("/query")
    public List<UserEntity> query(){
       return userService.query();
    }

    @RequestMapping("/insertFromXml")
    public String insertFromXml(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("xml");
        userEntity.setAge(29);
        userService.insert(userEntity);
        return "insert from xml success";
    }

}
