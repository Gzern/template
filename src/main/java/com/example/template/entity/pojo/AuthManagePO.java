package com.example.template.entity.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AuthManagePO implements Serializable {

    public static final Map<String, Object> AUTH_MAP;
    private static final Map<String, Map<String, Boolean>> MANAGER;//超级管理员
    private static final Map<String, Map<String, Boolean>> FACTORIER;//分厂管理员
    private static final Map<String, Map<String, Boolean>> NORMALER;//普通用户

    static {
        MANAGER = new HashMap<>();
        Map<String, Boolean> resourcePermission = new HashMap<>();
        resourcePermission.put("get,/login/sign", true);
        resourcePermission.put("get,/templateInfo/exist", true);
        resourcePermission.put("get,/templateInfo/list", true);
        resourcePermission.put("delete,/templateInfo/delete", true);
        resourcePermission.put("post,/templateInfo/add", true);
        resourcePermission.put("get,/templateStock/list", true);
        resourcePermission.put("post,/templateStock/add", true);
        resourcePermission.put("get,/templateStock/stockInfo", true);
        resourcePermission.put("post,/templateStock/in", true);
        resourcePermission.put("post,/templateStock/out", true);
        resourcePermission.put("post,/templateStock/lend", true);
        resourcePermission.put("delete,/templateStock/delete", true);
        resourcePermission.put("get,/userInfo/list", true);
        resourcePermission.put("get,/userInfo/userInfo", true);
        resourcePermission.put("get,/roleInfo/roleInfo", true);
        resourcePermission.put("post,/userInfo/updateUserRole", true);
        resourcePermission.put("get,/login/logout", true);
        resourcePermission.put("get,/templateStock/download", true);
        resourcePermission.put("get,/userInfo/delete", true);
        resourcePermission.put("get,/orderInfo/list", true);
        Map<String, Boolean> hashMenus = new HashMap<>();
        hashMenus.put("/add", true);
        hashMenus.put("/handle", true);
        hashMenus.put("/info", true);
        hashMenus.put("/store", true);
        hashMenus.put("/stock", true);
        hashMenus.put("/manager", true);
        hashMenus.put("/config", true);
        hashMenus.put("/log", true);
        MANAGER.put("resourcePermission", resourcePermission);
        MANAGER.put("hashMenus", hashMenus);
    }

    static {
        FACTORIER = new HashMap<>();
        Map<String, Boolean> resourcePermission = new HashMap<>();
        resourcePermission.put("get,/login/sign", true);
        resourcePermission.put("get,/templateInfo/exist", true);
        resourcePermission.put("get,/templateInfo/list", true);
        resourcePermission.put("post,/templateInfo/add", true);
        resourcePermission.put("get,/templateStock/list", true);
        resourcePermission.put("post,/templateStock/add", true);
        resourcePermission.put("get,/login/logout", true);
        resourcePermission.put("get,/templateStock/download", true);
        resourcePermission.put("get,/orderInfo/list", true);
        resourcePermission.put("get,/templateStock/stockInfo", true);
        resourcePermission.put("post,/templateStock/in", true);
        resourcePermission.put("post,/templateStock/out", true);
        resourcePermission.put("post,/templateStock/lend", true);
        resourcePermission.put("delete,/templateStock/delete", true);
        Map<String, Boolean> hashMenus = new HashMap<>();
        hashMenus.put("/add", true);
        hashMenus.put("/handle", true);
        hashMenus.put("/info", true);
        hashMenus.put("/store", true);
        hashMenus.put("/stock", true);
        hashMenus.put("/log", true);
        FACTORIER.put("resourcePermission", resourcePermission);
        FACTORIER.put("hashMenus", hashMenus);
    }

    static {
        NORMALER = new HashMap<>();
        Map<String, Boolean> resourcePermission = new HashMap<>();
        resourcePermission.put("get,/login/sign", true);
        resourcePermission.put("get,/templateInfo/list", true);
        resourcePermission.put("get,/templateStock/download", true);
        resourcePermission.put("get,/templateStock/list", true);
        resourcePermission.put("get,/login/logout", true);
        Map<String, Boolean> hashMenus = new HashMap<>();
        hashMenus.put("/info", true);
        hashMenus.put("/store", true);
        NORMALER.put("resourcePermission", resourcePermission);
        NORMALER.put("hashMenus", hashMenus);
    }

    static {
        AUTH_MAP = new HashMap<>();
        AUTH_MAP.put(RoleEnum.NORMAL.getName(), NORMALER);
        AUTH_MAP.put(RoleEnum.FACTORIER.getName(), FACTORIER);
        AUTH_MAP.put(RoleEnum.MANAGER.getName(), MANAGER);
    }
}
