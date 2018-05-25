package com.example.template.service;

import com.example.template.entity.domain.TemplateStock;
import com.example.template.entity.domain.TemplateStockPicture;
import com.example.template.entity.pojo.ResponseResult;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;

public interface ITemplateStockService {

    public boolean addTemplateStock(TemplateStock templateStock,
                                    List<TemplateStockPicture> pictures);

    public ResponseResult<TemplateStock> updateTemplateStock(TemplateStock templateStock);

    public boolean deleteTemplateStockByTemplateStockId(Long templateStockId);

    public boolean existedTemplateStock(TemplateStock templateStock);

    public Map<String, Object> listTemplateStockByExample(TemplateStock templateStock, int startNum, int pageSize);

    public int countTemplateStockInfoNum();

    public TemplateStock getTemplateStockById(Long id);

    public void downloadTemplateInfosByExample(Workbook workbook, TemplateStock templateStock);

}
