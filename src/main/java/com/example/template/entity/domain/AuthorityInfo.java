package com.example.template.entity.domain;

import java.io.Serializable;
import java.util.Date;

public class AuthorityInfo implements Serializable {

    private Integer id;
    private Integer authorityLevel;
    private String authorityDesc;
    private Date authorityCtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorityLevel() {
        return authorityLevel;
    }

    public void setAuthorityLevel(Integer authorityLevel) {
        this.authorityLevel = authorityLevel;
    }

    public String getAuthorityDesc() {
        return authorityDesc;
    }

    public void setAuthorityDesc(String authorityDesc) {
        this.authorityDesc = authorityDesc;
    }

    public Date getAuthorityCtime() {
        return authorityCtime;
    }

    public void setAuthorityCtime(Date authorityCtime) {
        this.authorityCtime = authorityCtime;
    }

    @Override
    public String toString() {
        return "AuthorityInfo{" +
                "id=" + id +
                ", authorityLevel=" + authorityLevel +
                ", authorityDesc='" + authorityDesc + '\'' +
                ", authorityCtime=" + authorityCtime +
                '}';
    }
}
