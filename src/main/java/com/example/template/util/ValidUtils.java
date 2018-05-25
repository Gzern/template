package com.example.template.util;

import com.example.template.entity.pojo.Result;
import com.example.template.exception.CustomValidException;
import org.springframework.validation.BindingResult;

public class ValidUtils {

    /**
     * 验证前台参数是否有错
     * @param bindingResult
     * @throws CustomValidException
     */
    public static void validHepler(BindingResult bindingResult) throws CustomValidException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidException(Result.VALIDFAIED.getMsg(), bindingResult);
        }
    }
}
