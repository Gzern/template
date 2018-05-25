package com.example.template.mapper;

import com.example.template.entity.domain.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserRoleMapper {
    void addUserRole(UserRole userRole);

    void deleteUserRoleByUserId(@Param("userId") Integer userId);

    List<UserRole> selectAllUserRoleByUserId(@Param("userId") Integer userId);
}
