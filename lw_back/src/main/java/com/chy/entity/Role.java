package com.chy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("roles")
@Data
public class Role {
    @TableId
    private Long id;
    private String name;

    // Getters and Setters
}