package com.example.template.entity.domain;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {

    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "用户名密码不能为空")
    private String userPassword;
    private Date userCtime;
    private String userFactory;

    public String getUserFactory() {
        return userFactory;
    }

    public void setUserFactory(String userFactory) {
        this.userFactory = userFactory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getUserCtime() {
        return userCtime;
    }

    public void setUserCtime(Date userCtime) {
        this.userCtime = userCtime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userCtime=" + userCtime +
                ", userFactory='" + userFactory + '\'' +
                '}';
    }
}
