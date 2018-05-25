package com.example.template.service;

import com.example.template.entity.domain.TemplateStockPicture;

import java.util.List;

public interface ITemplateStockPicService {

    public boolean addPic(TemplateStockPicture templateStockPicture);

    public List<TemplateStockPicture> listPics(TemplateStockPicture templateStockPicture);

}
