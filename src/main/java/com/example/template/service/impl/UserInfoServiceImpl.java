package com.example.template.service.impl;

import com.example.template.entity.domain.RoleInfo;
import com.example.template.entity.domain.UserInfo;
import com.example.template.mapper.IRoleInfoMapper;
import com.example.template.mapper.IUserInfoMapper;
import com.example.template.service.IUserInfoService;
import com.example.template.service.IUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class UserInfoServiceImpl implements IUserInfoService {

    private Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private IUserInfoMapper iUserInfoMapper;

    @Autowired
    private IRoleInfoMapper iRoleInfoMapper;

    @Autowired
    private IUserRoleService iUserRoleService;

    @Override
    public boolean addUserInfo(UserInfo userInfo) {
        logger.info("添加用户,{}", userInfo);
        if (isExistUserName(userInfo.getUserName())) {
            logger.info("添加失败,用户名已经存在");
            return false;
        }
        iUserInfoMapper.addUserInfo(userInfo);
        List<RoleInfo> list = new LinkedList<>();
        RoleInfo roleInfo = iRoleInfoMapper.findRoleIdByRoleName("普通用户");
        list.add(roleInfo);
        iUserRoleService.addUserRole(userInfo, list);
        logger.info("添加用户成功");
        return true;
    }

    @Override
    public UserInfo findUserInfo(UserInfo userInfo) {
        logger.info("查询用户信息,{}", userInfo);
        return iUserInfoMapper.findUserInfo(userInfo);
    }

    @Override
    public boolean isExistUserName(String userName) {
        logger.info("检测用户名是否存在,{}", userName);
        Integer userInfoByUserName = iUserInfoMapper.findUserInfoByUserName(userName);
        logger.info("查询用户名存在结果,{}", userInfoByUserName);
        return userInfoByUserName != null && userInfoByUserName > 0;
    }

    @Override
    public int count() {
        return iUserInfoMapper.countAllUserInfos();
    }
}
