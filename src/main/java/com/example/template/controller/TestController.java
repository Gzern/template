package com.example.template.controller;

import com.example.template.entity.domain.TemplateStock;
import com.example.template.entity.domain.TemplateStockPicture;
import com.example.template.entity.vo.TemplateStockVO;
import com.example.template.service.ITemplateStockService;
import com.example.template.util.ExcelUtils;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    ITemplateStockService iTemplateStockService;


    @RequestMapping("/swagger/download")
    public void download(HttpServletResponse response) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        TemplateStock templateStockById = iTemplateStockService.getTemplateStockById(1001L);
        TemplateStockVO templateStockVO = new TemplateStockVO();
        templateStockVO.setTemplateStock(templateStockById);
        templateStockVO.setImages(new LinkedList<TemplateStockPicture>());

//        iTemplateStockService.downloadTemplateInfosByExample(workbook, templateStockById);
        ExcelUtils.list2excel(workbook, Lists.newArrayList(templateStockVO));
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

}
