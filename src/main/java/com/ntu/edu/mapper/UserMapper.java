package com.ntu.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ntu.edu.model.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    @Insert("insert into user(name, age)values(#{name}, #{age})")
    int insert(UserEntity userEntity);

    @Select("select * from user")
    List<UserEntity> query();

    int insertUsingXml(UserEntity userEntity);

    int deleteUsingXml(int id);

}
