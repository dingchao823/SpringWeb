package com.ntu.edu.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("user")
@Data
public class UserEntity implements Serializable {

    @TableId("_id")
    private int id;

    private String name;

    private int age;

    private Date birthday;

}
