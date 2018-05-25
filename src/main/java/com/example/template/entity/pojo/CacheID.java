package com.example.template.entity.pojo;

public enum CacheID {

    USER("_user"),
    ROLE("_role");

    private String desc;

    private CacheID(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
