package com.ntu.edu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("user")
@Data
public class UserEntity {

    private String name;

    private int age;

    private Date birthday;

}
