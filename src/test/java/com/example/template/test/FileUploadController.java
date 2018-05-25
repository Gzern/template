package com.example.template.test;


import com.example.template.entity.domain.TemplateStock;
import com.example.template.entity.vo.TemplateStockVO;
import com.example.template.service.ITemplateStockService;
import com.example.template.util.ExcelUtils;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@RestController
public class FileUploadController {

    @Autowired
    ITemplateStockService iTemplateStockService;


    @RequestMapping("/upload")
    public String upload(@RequestParam("images") String images) {
        System.out.println(images);
        if (images == null) return "ERROR";
        return "OK";
    }
}
