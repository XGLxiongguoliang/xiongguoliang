package com.example.network.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program com.example.demo.domain
 * @description user
 * @auther Mr.Xiong
 * @create 2020-03-07 12:53
 */
@Getter
@Setter
@ToString
public class UserInfo {

    private Integer id;
    private String name;
    private String username;
    private Integer age;
    private Integer flag;
    private String account;
    private String password;
    private String role;

    public UserInfo() {
    }

    public UserInfo(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
