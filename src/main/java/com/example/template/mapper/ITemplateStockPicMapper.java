package com.example.template.mapper;

import com.example.template.entity.domain.TemplateStockPicture;

import java.util.List;

public interface ITemplateStockPicMapper {

    public void addPic(TemplateStockPicture templateStockPicture);

    public List<TemplateStockPicture> listPics(TemplateStockPicture templateStockPicture);

    public void deletePicByStockId(Long stockId);
}
