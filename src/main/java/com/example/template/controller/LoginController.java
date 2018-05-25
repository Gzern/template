package com.example.template.controller;

import com.alibaba.fastjson.JSON;
import com.example.template.entity.domain.RoleInfo;
import com.example.template.entity.domain.UserInfo;
import com.example.template.entity.pojo.*;
import com.example.template.entity.vo.UserRoleVO;
import com.example.template.exception.CustomValidException;
import com.example.template.service.IUserInfoService;
import com.example.template.service.IUserRoleService;
import com.example.template.service.impl.RedisServiceImpl;
import com.example.template.util.ValidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private RedisServiceImpl redisService;

    @Autowired
    private IUserInfoService iUserInfoService;

    @Autowired
    private IUserRoleService iUserRoleService;

    @PostMapping("/login")
    public ResponseResult<String> login(@RequestBody @Valid UserInfo userInfo, BindingResult bindingResult)
            throws CustomValidException {
        ValidUtils.validHepler(bindingResult);
        UserInfo userInfo1;
        if ((userInfo1 = iUserInfoService.findUserInfo(userInfo)) != null) {
            UserRoleVO userRoleByUserId = iUserRoleService.findUserRoleByUserId(userInfo1.getId());
            List<RoleInfo> roleInfos = userRoleByUserId.getRoleInfos();
            String token = UUID.randomUUID().toString();
            token = token.replaceAll("-", "");
            redisService.setValue(token + CacheID.USER.getDesc(),
                    JSON.toJSONString(userInfo1), 7200L);
            redisService.setValue(token + CacheID.ROLE.getDesc(),
                    JSON.toJSONString(roleInfos.get(0)), 7200L);
            return new ResponseResult(Result.SUCCESS, token);
        } else {
            return new ResponseResult(Result.FAILED);
        }
    }

    @GetMapping("/sign")
    public ResponseResult<Map<String, Object>> getUserInfo(String token) {
        ResponseResult<Map<String, Object>> result = new ResponseResult<>();
        Map<String, Object> map = new HashMap<>();
        UserInfo userInfo = JSON.parseObject(redisService.getValue(token + CacheID.USER.getDesc()), UserInfo.class);
        RoleInfo roleInfo = JSON.parseObject(redisService.getValue(token + CacheID.ROLE.getDesc()), RoleInfo.class);
        userInfo.setUserPassword("");
        map.put("roleName", RoleEnum.getRoleByDesc(roleInfo.getRoleName()).getName());
        map.put("auth", AuthManagePO.AUTH_MAP.get(RoleEnum.getRoleByDesc(roleInfo.getRoleName()).getName()));
        map.put("userInfo", userInfo);
        map.put("roleInfo", roleInfo);
        result.setSuccess(Result.SUCCESS.getMsg());
        result.setData(map);
        return result;
    }

    @PostMapping("/register")
    public ResponseResult<String> register(@RequestBody @Valid UserInfo userInfo, BindingResult bindingResult)
            throws CustomValidException {
        ValidUtils.validHepler(bindingResult);
        System.out.println(userInfo);
        userInfo.setUserCtime(new Date());
        ResponseResult<String> responseResult = new ResponseResult<>();
        if (iUserInfoService.isExistUserName(userInfo.getUserName())) {
            responseResult.setSuccess(Result.FAILED.getMsg());
            responseResult.setData("用户名已经存在");
        } else {
            iUserInfoService.addUserInfo(userInfo);
//            responseResult.setSuccess(Result.SUCCESS.getMsg());
            //注册成功后,直接登录
            responseResult = login(userInfo, bindingResult);
        }
        return responseResult;
    }

    @GetMapping("/logout")
    public ResponseResult logout(String token) {
        if (
                redisService.deleteKey(token + CacheID.USER.getDesc()) &&
                        redisService.deleteKey(token + CacheID.ROLE.getDesc())
                ) {
            return new ResponseResult(Result.SUCCESS);
        }
        return new ResponseResult(Result.FAILED);
    }

}
