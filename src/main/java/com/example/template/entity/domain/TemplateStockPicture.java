package com.example.template.entity.domain;

import java.io.Serializable;
import java.util.Date;

public class TemplateStockPicture implements Serializable {

    private Long stockId;
    private String pictureUrl;
    private Date pictureCtime;

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Date getPictureCtime() {
        return pictureCtime;
    }

    public void setPictureCtime(Date pictureCtime) {
        this.pictureCtime = pictureCtime;
    }

    @Override
    public String toString() {
        return "TemplateStockPicture{" +
                "stockId=" + stockId +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", pictureCtime=" + pictureCtime +
                '}';
    }
}
