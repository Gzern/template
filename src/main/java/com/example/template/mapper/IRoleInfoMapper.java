package com.example.template.mapper;

import com.example.template.entity.domain.RoleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleInfoMapper {

    List<RoleInfo> selectAllRoleInfosOrByRoleId(@Param("id") Integer id);

    RoleInfo findRoleIdByRoleName(String roleName);

}
