package com.example.shiro;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
//登录成功之后返回的一个用户信息的载体
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;

    private LocalDateTime created;
}
