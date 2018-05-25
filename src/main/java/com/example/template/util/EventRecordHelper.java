package com.example.template.util;

import com.example.template.entity.pojo.UserRolePO;

/**
 * 记录当前操作用户的信息
 */
public class EventRecordHelper {

    private static final ThreadLocal<UserRolePO> EVENT_HELPER = new ThreadLocal<>();

    /**
     * 从线程本地变量获取数据
     *
     * @return
     */
    public static UserRolePO getUserRolePO() {
        return EVENT_HELPER.get();
    }

    public static void setUserRolePO(UserRolePO userRolePO) {
        EVENT_HELPER.set(userRolePO);
    }

    public static void removeUserRolePO() {
        UserRolePO userRolePO = EVENT_HELPER.get();
        if (userRolePO != null) {
            userRolePO = null;
            EVENT_HELPER.set(userRolePO);
        }
        EVENT_HELPER.remove();
    }
}
