package com.example.template.controller;

import com.example.template.entity.domain.OrderInfo;
import com.example.template.entity.pojo.ResponseResult;
import com.example.template.entity.pojo.Result;
import com.example.template.mapper.IOrderInfoMapper;
import com.example.template.util.EventRecordHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController {

    @Autowired
    private IOrderInfoMapper iOrderInfoMapper;

    @GetMapping("/list")
    public ResponseResult listOrderInfos(OrderInfo orderInfo
            , @RequestParam(value = "startPage", defaultValue = "1")
                                                 int startPage
            , @RequestParam(value = "pageSize", defaultValue = "10")
                                                 int pageSize) {
        /**
         * 判断当前用户是否是分厂管理员
         * 如果是则分厂管理员则设置分厂属性,只能查询该分厂所属的信息
         */
        String userFactory = EventRecordHelper.getUserRolePO().getUserFactory();
        if (StringUtils.isNotBlank(userFactory)) {
            orderInfo.setOpFactory(userFactory);
        }
        PageHelper.startPage(startPage, pageSize);
        List<OrderInfo> orderInfos = iOrderInfoMapper.selectAllOrderInfosOrByExample(orderInfo);
        PageInfo<OrderInfo> pageInfo = new PageInfo<>(orderInfos);
        Map<String, Object> map = new HashMap<>();
        map.put("orderList", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return new ResponseResult(Result.SUCCESS, map);
    }

}
