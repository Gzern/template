package com.example.template.controller;


import com.example.template.entity.domain.RoleInfo;
import com.example.template.entity.pojo.ResponseResult;
import com.example.template.entity.pojo.Result;
import com.example.template.service.IRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roleInfo")
public class RoleInfoController {

    @Autowired
    private IRoleInfoService iRoleInfoService;

    @GetMapping("/roleInfo")
    public ResponseResult<List<RoleInfo>> listUserInfo(@RequestParam(value = "startPage", defaultValue = "1")
                                                               int startPage,
                                                       @RequestParam(value = "pageSize", defaultValue = "100")
                                                               int pageSize) {
        return new ResponseResult<>(Result.SUCCESS, iRoleInfoService.findAllRoleInfos(startPage, pageSize));
    }
}
