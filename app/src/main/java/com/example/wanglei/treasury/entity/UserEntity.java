package com.example.wanglei.treasury.entity;

/**
 * Created by wanglei on 2017/7/6.
 */

public class UserEntity {
    private String username; //用户名
    private String userpassword; //密码
    private String name; //姓名


    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
