package com.example.template.service.impl;

import com.example.template.entity.domain.RoleInfo;
import com.example.template.entity.domain.UserInfo;
import com.example.template.entity.domain.UserRole;
import com.example.template.entity.vo.UserRoleVO;
import com.example.template.mapper.IRoleInfoMapper;
import com.example.template.mapper.IUserInfoMapper;
import com.example.template.mapper.IUserRoleMapper;
import com.example.template.service.IUserRoleService;
import com.example.template.util.EventRecordHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserRoleServiceImpl implements IUserRoleService {

    private Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Autowired
    private IUserInfoMapper iUserInfoMapper;
    @Autowired
    private IUserRoleMapper iUserRoleMapper;
    @Autowired
    private IRoleInfoMapper iRoleInfoMapper;

    @Override
    public Map<String, Object> listUserRoles(String userName, int startPage, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        logger.info("查询用户角色信息,{}", userName);
        List<UserRoleVO> list = new LinkedList<>();
        PageHelper.startPage(startPage, pageSize);
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUserName(userName);
        userInfo1.setId(EventRecordHelper.getUserRolePO().getUserId());
        List<UserInfo> userInfos1 = iUserInfoMapper.selectAllUserInfosOrByUserName(userInfo1);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfos1);
        List<UserInfo> userInfos = pageInfo.getList();
        map.put("totalCount", pageInfo.getTotal());
        logger.info("查询用户结果,{}", userInfos);
        if (userInfos != null && !userInfos.isEmpty()) {
            for (UserInfo userInfo : userInfos) {
                UserRoleVO roleVO = findUserRoleByUserId(userInfo.getId());
                list.add(roleVO);
            }
        }
        logger.info("查询用户角色关联关系完毕结果,{}", list);
        map.put("userList", list);
        return map;
    }

    @Override
    public void addUserRole(UserInfo userInfo, List<RoleInfo> list) {
        UserInfo userInfo1 = iUserInfoMapper.findUserInfo(userInfo);
        logger.info("添加用户角色关联关系,{}", userInfo1);
        if (list != null && !list.isEmpty()) {
            for (RoleInfo roleInfo : list) {
                //如果角色是分厂管理员,则还要更新用户的分厂信息
                List<RoleInfo> roleInfoList = iRoleInfoMapper.selectAllRoleInfosOrByRoleId(roleInfo.getId());
                if (roleInfoList.get(0).isRoleFactory()) {
                    iUserInfoMapper.updateUserInfoFactory(userInfo);
                }
                logger.info("添加角色关系,{}", roleInfo);
                UserRole userRole = new UserRole();
                userRole.setUserId(userInfo1.getId());
                userRole.setRoleId(roleInfo.getId());
                iUserRoleMapper.addUserRole(userRole);
            }
        }
    }

    @Override
    public void updateUserRoleByUserId(UserInfo userInfo, List<RoleInfo> list) {
        logger.info("update userRole.userId={},role={}", userInfo.getId(), list);
        UserInfo info = iUserInfoMapper.findUserInfoByUserId(userInfo.getId());
        //清空用户的分厂属性
        info.setUserFactory("");
        iUserInfoMapper.updateUserInfoFactory(info);
        logger.info("find userInfo ,{}", info);
        iUserRoleMapper.deleteUserRoleByUserId(userInfo.getId());
        info.setUserFactory(userInfo.getUserFactory());
        addUserRole(info, list);
    }

    @Override
    public UserRoleVO findUserRoleByUserId(int id) {
        UserRoleVO roleVO = new UserRoleVO();
        UserInfo userInfo = iUserInfoMapper.findUserInfoByUserId(id);
        userInfo.setUserPassword("******");
        List<UserRole> userRoles = iUserRoleMapper.selectAllUserRoleByUserId(userInfo.getId());
        logger.info("查询用户角色关联关系,{}", userRoles);
        List<RoleInfo> roleInfos = new LinkedList<>();
        for (UserRole userRole : userRoles) {
            logger.info("用户关联关系,{}", userRole);
            List<RoleInfo> roleInfos1 = iRoleInfoMapper.selectAllRoleInfosOrByRoleId(userRole.getRoleId());
            logger.info("查询对应角色结果,{}", roleInfos1);
            roleInfos.add(roleInfos1.get(0));
        }
        roleVO.setUserInfo(userInfo);
        roleVO.setRoleInfos(roleInfos);
        return roleVO;
    }

    @Override
    public void deleteUserRole(int userId) {
        iUserRoleMapper.deleteUserRoleByUserId(userId);
        iUserInfoMapper.deleteUserInfoById(userId);
    }
}
