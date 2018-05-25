package com.example.template.entity.pojo;

import java.io.Serializable;

public class UserRolePO implements Serializable{

    private Integer userId;
    private String userName;
    private String userFactory;
    private String roleName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFactory() {
        return userFactory;
    }

    public void setUserFactory(String userFactory) {
        this.userFactory = userFactory;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserRolePO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userFactory='" + userFactory + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
