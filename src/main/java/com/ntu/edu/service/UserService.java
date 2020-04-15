package com.ntu.edu.service;

import com.ntu.edu.entity.UserEntity;

import java.util.List;

public interface UserService {

    int insert(UserEntity userEntity);

    List<UserEntity> query();

    int insertFromXml(UserEntity userEntity);
}
