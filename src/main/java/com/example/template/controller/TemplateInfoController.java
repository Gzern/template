package com.example.template.controller;

import com.example.template.entity.domain.OrderInfo;
import com.example.template.entity.domain.TemplateInfo;
import com.example.template.entity.pojo.ResponseResult;
import com.example.template.entity.pojo.Result;
import com.example.template.entity.pojo.UserRolePO;
import com.example.template.exception.CustomValidException;
import com.example.template.mapper.IOrderInfoMapper;
import com.example.template.service.ITemplateInfoService;
import com.example.template.util.EventRecordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/templateInfo")
public class TemplateInfoController {

    private Logger logger = LoggerFactory.getLogger(TemplateInfoController.class);

    @Autowired
    private ITemplateInfoService iTemplateInfoService;

    @Autowired
    private IOrderInfoMapper iOrderInfoMapper;

    @RequestMapping("/list")
    public ResponseResult<Map<String, Object>> list(String templateId,
                                                    @RequestParam(value = "startPage", defaultValue = "1")
                                                            int startPage,
                                                    @RequestParam(value = "pageSize", defaultValue = "100")
                                                            int pageSize) {
        logger.info("开始显示所有样板信息");
        Map<String, Object> map = iTemplateInfoService.listTemplateInfos(templateId, startPage, pageSize);
        ResponseResult<Map<String, Object>> result = new ResponseResult<>();
        result.setSuccess(Result.SUCCESS.getMsg());
        result.setData(map);
        return result;
    }

    @RequestMapping("/add")
    public String addTemplateInfo(@RequestBody @Valid TemplateInfo templateInfo, BindingResult bindingResult)
            throws CustomValidException {
        logger.info("添加样板元信息,{}", templateInfo);
        if (bindingResult.hasErrors()) {
            throw new CustomValidException(Result.VALIDFAIED.getMsg(), bindingResult);
        }
        templateInfo.setTemplateSignatureTime(new Date());
        if (iTemplateInfoService.addTemplateInfo(templateInfo)) {
            UserRolePO userRolePO = EventRecordHelper.getUserRolePO();
            //插入添加记录
            OrderInfo orderInfo = new OrderInfo.OrderInfoBuilder(userRolePO)
                    .buildOpBehavier("添加样板基本信息")
                    .build();
            iOrderInfoMapper.insertOrderInfo(orderInfo);
            return Result.OK.getMsg();
        } else {
            return Result.ERROR.getMsg();
        }
    }

    @RequestMapping("/exist")
    public String existed(String templateId) {
        logger.info("检查样板信息是否存在,样板ID为{}", templateId);
        TemplateInfo templateInfo = new TemplateInfo();
        templateInfo.setTemplateId(templateId);
        if (iTemplateInfoService.existed(templateInfo)) {
            return Result.EXISTED.getMsg();
        } else {
            return "";
        }
    }

    @RequestMapping("/delete")
    public String deleteTemplate(@RequestParam String templateId) {
        logger.info("delete templateInfo,id is = {}", templateId);
        TemplateInfo templateInfo = new TemplateInfo();
        templateInfo.setTemplateId(templateId);
        try {
            iTemplateInfoService.deleteTemplateInfo(templateInfo);
            UserRolePO userRolePO = EventRecordHelper.getUserRolePO();
            OrderInfo orderInfo = new OrderInfo.OrderInfoBuilder(userRolePO)
                    .buildOpBehavier("报废样板信息")
                    .build();
            iOrderInfoMapper.insertOrderInfo(orderInfo);
            return Result.SUCCESS.getMsg();
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("delete templateInfo error,error is = {}", e);
            return Result.FAILED.getMsg();
        }
    }

}
