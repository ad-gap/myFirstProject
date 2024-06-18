package com.chy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("users")
@Data
public class User {
    @TableId
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Boolean enabled;

    // Getters and Setters
}