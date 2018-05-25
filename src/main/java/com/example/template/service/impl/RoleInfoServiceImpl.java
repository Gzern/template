package com.example.template.service.impl;

import com.example.template.entity.domain.RoleInfo;
import com.example.template.mapper.IRoleInfoMapper;
import com.example.template.service.IRoleInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleInfoServiceImpl implements IRoleInfoService {

    @Autowired
    private IRoleInfoMapper iRoleInfoMapper;

    @Override
    public List<RoleInfo> findAllRoleInfos(int startPage, int pageSize) {
        PageHelper.startPage(startPage, pageSize);
        List<RoleInfo> roleInfos = iRoleInfoMapper.selectAllRoleInfosOrByRoleId(null);
        return roleInfos;
    }
}
