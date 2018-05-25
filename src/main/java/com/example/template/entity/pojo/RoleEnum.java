package com.example.template.entity.pojo;


public enum RoleEnum {


    NORMAL("normal", "普通用户", 0),
    FACTORIER("factorier", "分厂管理员", 1),
    MANAGER("manager", "超级管理员", 2);

    //role name
    private String name;
    //role describe
    private String desc;
    /*
     *   role auth_level
     *   0:normal 1:factorier 2:manager
     */
    private int level;

    RoleEnum(String name, String desc, int level) {
        this.name = name;
        this.desc = desc;
        this.level = level;
    }

    public static RoleEnum getRoleByDesc(String desc) {
        switch (desc) {
            case "分厂管理员":
                return FACTORIER;
            case "超级管理员":
                return MANAGER;
            default:
                return NORMAL;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
