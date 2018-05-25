package com.example.template.mapper;

import com.example.template.entity.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserInfoMapper {

    void addUserInfo(UserInfo userInfo);

    UserInfo findUserInfo(UserInfo userInfo);

    UserInfo findUserInfoByUserId(Integer userId);

    Integer findUserInfoByUserName(@Param("userName") String userName);

    List<UserInfo> selectAllUserInfosOrByUserName(UserInfo userInfo);

    Integer countAllUserInfos();

    void updateUserInfoFactory(UserInfo userInfo);

    void deleteUserInfoById(int id);
}
