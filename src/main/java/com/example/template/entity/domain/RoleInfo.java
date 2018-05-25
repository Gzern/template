package com.example.template.entity.domain;

import java.io.Serializable;
import java.util.Date;

public class RoleInfo implements Serializable {

    private Integer id;
    private String roleName;
    private boolean roleFactory;
    private Date roleCtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isRoleFactory() {
        return roleFactory;
    }

    public void setRoleFactory(boolean roleFactory) {
        this.roleFactory = roleFactory;
    }

    public Date getRoleCtime() {
        return roleCtime;
    }

    public void setRoleCtime(Date roleCtime) {
        this.roleCtime = roleCtime;
    }


    @Override
    public String toString() {
        return "RoleInfo{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleFactory=" + roleFactory +
                ", roleCtime=" + roleCtime +
                '}';
    }
}
