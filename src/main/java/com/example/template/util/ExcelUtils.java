package com.example.template.util;

import com.example.template.entity.domain.TemplateStock;
import com.example.template.entity.domain.TemplateStockPicture;
import com.example.template.entity.vo.TemplateStockVO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public class ExcelUtils {

    public static void list2excel(Workbook workbook, List<TemplateStockVO> list) {
        Sheet sheet = workbook.createSheet("样板库存信息");
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("样板编号");
        row0.createCell(1).setCellValue("样板数量");
        row0.createCell(2).setCellValue("样板套数");
        row0.createCell(3).setCellValue("产区");
        row0.createCell(4).setCellValue("分厂");
        row0.createCell(5).setCellValue("货架编号");
        row0.createCell(6).setCellValue("存放时间");
        row0.createCell(7).setCellValue("备注");
        for (int i = 0; i < list.size(); i++) {
            Row row = sheet.createRow(i + 1);
            TemplateStockVO templateStockVO = list.get(i);
            TemplateStock stock = templateStockVO.getTemplateStock();
            List<TemplateStockPicture> images = templateStockVO.getImages();
            row.createCell(0).setCellValue(stock.getTemplateId());
            row.createCell(1).setCellValue(stock.getTemplateAmount());
            row.createCell(2).setCellValue(stock.getTemplateSuits());
            row.createCell(3).setCellValue(stock.getTemplateAreas());
            row.createCell(4).setCellValue(stock.getTemplateFactory());
            row.createCell(5).setCellValue(stock.getTemplateStore());
            /*StringBuilder sb = new StringBuilder();
            for (TemplateStockPicture image : images) {
                sb.append(image.getPictureUrl() + ";");
            }
            row.createCell(6).setCellValue(sb.toString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            row.createCell(7).setCellValue(images.isEmpty() ?
                    "无" : dateFormat.format(images.get(0).getPictureCtime()));*/
            row.createCell(6).setCellValue(stock.getTemplateCtime() == null
                    ? "无" : stock.getTemplateCtime().toString());
            row.createCell(7).setCellValue(stock.getTemplateMemo());
        }
    }
}
