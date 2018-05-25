package com.example.template.mapper;

import com.example.template.entity.domain.TemplateStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITemplateStockMapper {

    public void addTemplateStock(TemplateStock templateStock);

    public void updateTemplateStock(TemplateStock templateStock);

    public List<TemplateStock> listTemplateStocksByExample(TemplateStock templateStock);

    public void deleteTemplateStockByTemplateId(@Param("templateId") String templateId);

    void deleteTemplateStockByStockId(@Param("stockId") Long stockId);

    public TemplateStock findTemplateStock(TemplateStock templateStock);

    public TemplateStock findTemplateStockById(Long id);

    public List<TemplateStock> findTemplateStockByTemplateId(@Param("templateId") String templateId);

    public Integer countTemplateStockInfoNum();

}
