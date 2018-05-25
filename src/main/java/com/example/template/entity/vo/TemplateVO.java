package com.example.template.entity.vo;

import com.example.template.entity.domain.TemplateStock;
import com.example.template.entity.domain.TemplateStockPicture;

public class TemplateVO {

    private TemplateStock templateStock;

    private TemplateStockPicture templateStockPicture;

    public TemplateStock getTemplateStock() {
        return templateStock;
    }

    public void setTemplateStock(TemplateStock templateStock) {
        this.templateStock = templateStock;
    }

    public TemplateStockPicture getTemplateStockPicture() {
        return templateStockPicture;
    }

    public void setTemplateStockPicture(TemplateStockPicture templateStockPicture) {
        this.templateStockPicture = templateStockPicture;
    }
}
