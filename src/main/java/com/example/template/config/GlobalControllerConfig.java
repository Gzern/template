package com.example.template.config;

import com.example.template.entity.pojo.Result;
import com.example.template.exception.CustomValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerConfig {

    private Logger logger = LoggerFactory.getLogger(GlobalControllerConfig.class);

    @ExceptionHandler(value = CustomValidException.class)
    @ResponseBody
    public Map<String, Object> customValidException(CustomValidException cve) {
        logger.info("参数校验异常,{}", cve);
        Map<String, Object> map = new HashMap<>();
        map.put("code", cve.getMessage());
        BindingResult bindingResult = cve.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        map.put("errors", allErrors);
        return map;
    }

    /*@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String customValidException2() {
        return Result.NOT_FOUND_404.getMsg();
    }*/

}
