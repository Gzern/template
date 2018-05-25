package com.example.template.entity.vo;

import com.example.template.entity.domain.TemplateStock;
import com.example.template.entity.domain.TemplateStockPicture;

import java.io.Serializable;
import java.util.List;

public class TemplateStockVO implements Serializable {

    private TemplateStock templateStock;
    private List<TemplateStockPicture> images;

    public TemplateStock getTemplateStock() {
        return templateStock;
    }

    public void setTemplateStock(TemplateStock templateStock) {
        this.templateStock = templateStock;
    }

    public List<TemplateStockPicture> getImages() {
        return images;
    }

    public void setImages(List<TemplateStockPicture> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "TemplateStockVO{" +
                "templateStock=" + templateStock +
                ", images=" + images +
                '}';
    }
}
