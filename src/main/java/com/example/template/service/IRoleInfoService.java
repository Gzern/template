package com.example.template.service;

import com.example.template.entity.domain.RoleInfo;

import java.util.List;

public interface IRoleInfoService {

    List<RoleInfo> findAllRoleInfos(int startPage, int pageSize);
}
