package com.example.template.entity.vo;

import com.example.template.entity.domain.UserInfo;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UpdateUserRoleVO implements Serializable {

    private UserInfo userInfo;
    @NotBlank(message = "角色ID不能为空")
    private Integer roleId;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UpdateUserRoleVO{" +
                "userInfo=" + userInfo +
                ", roleId=" + roleId +
                '}';
    }
}
