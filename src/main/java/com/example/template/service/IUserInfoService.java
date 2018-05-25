package com.example.template.service;

import com.example.template.entity.domain.UserInfo;

public interface IUserInfoService {

    boolean addUserInfo(UserInfo userInfo);

    UserInfo findUserInfo(UserInfo userInfo);

    boolean isExistUserName(String userName);

    int count();
}
