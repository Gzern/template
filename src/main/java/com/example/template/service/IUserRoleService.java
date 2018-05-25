package com.example.template.service;

import com.example.template.entity.domain.RoleInfo;
import com.example.template.entity.domain.UserInfo;
import com.example.template.entity.vo.UserRoleVO;

import java.util.List;
import java.util.Map;

public interface IUserRoleService {

    Map<String, Object> listUserRoles(String userName, int startPage, int pageSize);

    public void addUserRole(UserInfo userInfo, List<RoleInfo> list);

    void updateUserRoleByUserId(UserInfo userInfo, List<RoleInfo> list);

    UserRoleVO findUserRoleByUserId(int id);

    void deleteUserRole(int id);

}
