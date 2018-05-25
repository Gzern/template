package com.example.template.controller;

import com.example.template.entity.domain.RoleInfo;
import com.example.template.entity.pojo.ResponseResult;
import com.example.template.entity.pojo.Result;
import com.example.template.entity.vo.UpdateUserRoleVO;
import com.example.template.entity.vo.UserRoleVO;
import com.example.template.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private IUserRoleService iUserRoleService;

    @RequestMapping(value = "/list")
    public ResponseResult<Map<String, Object>> listUserInfo(@RequestParam(defaultValue = "") String userName,
                                                            @RequestParam(value = "startPage", defaultValue = "1")
                                                                    int startPage,
                                                            @RequestParam(value = "pageSize", defaultValue = "100")
                                                                    int pageSize) {
        ResponseResult<Map<String, Object>> responseResult = new ResponseResult<>();
        Map<String, Object> map = iUserRoleService.listUserRoles(userName, startPage, pageSize);
        responseResult.setSuccess(Result.SUCCESS.getMsg());
        responseResult.setData(map);
        return responseResult;
    }

    @RequestMapping("/userInfo")
    public ResponseResult<UserRoleVO> getUserRoleVO(int userId) {
        UserRoleVO userRoleByUserId = iUserRoleService.findUserRoleByUserId(userId);
        return new ResponseResult<>(Result.SUCCESS, userRoleByUserId);
    }

    @GetMapping("/delete")
    public String deleteUser(int userId) {
        iUserRoleService.deleteUserRole(userId);
        return Result.SUCCESS.getMsg();
    }

    @PostMapping("/updateUserRole")
    public ResponseResult updateUserRole(@RequestBody UpdateUserRoleVO updateUserRoleVO) {
        System.out.println(updateUserRoleVO);
        List<RoleInfo> list = new LinkedList<>();
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setId(updateUserRoleVO.getRoleId());
        list.add(roleInfo);
        iUserRoleService.updateUserRoleByUserId(updateUserRoleVO.getUserInfo(), list);
        return new ResponseResult(Result.SUCCESS);
    }
}