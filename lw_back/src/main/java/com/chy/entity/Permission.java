package com.chy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("permissions")
@Data
public class Permission {
    @TableId
    private Long id;
    private String name;
    private String description;

    // Getters and Setters
}