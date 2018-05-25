package com.example.template.entity.vo;

import com.example.template.entity.domain.RoleInfo;
import com.example.template.entity.domain.UserInfo;

import java.io.Serializable;
import java.util.List;

public class UserRoleVO implements Serializable {

    private UserInfo userInfo;
    private List<RoleInfo> roleInfos;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<RoleInfo> getRoleInfos() {
        return roleInfos;
    }

    public void setRoleInfos(List<RoleInfo> roleInfos) {
        this.roleInfos = roleInfos;
    }

    @Override
    public String toString() {
        return "UserRoleVO{" +
                "userInfo=" + userInfo +
                ", roleInfos=" + roleInfos +
                '}';
    }
}
