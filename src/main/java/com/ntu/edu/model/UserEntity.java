package com.ntu.edu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class UserEntity {

    private String name;

    private int age;

    private Date birthday;

}
