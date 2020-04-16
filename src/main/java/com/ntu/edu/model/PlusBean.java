package com.ntu.edu.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "plusTest")
public class PlusBean {

    @TableId(value = "id")
    private int id;

    private String name;

}
