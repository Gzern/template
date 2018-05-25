package com.example.template.service.impl;

import com.example.template.entity.domain.TemplateStockPicture;
import com.example.template.mapper.ITemplateStockPicMapper;
import com.example.template.service.ITemplateStockPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateStockPicServiceImpl implements ITemplateStockPicService {

    @Autowired
    private ITemplateStockPicMapper iTemplateStockPicMapper;

    @Override
    public boolean addPic(TemplateStockPicture templateStockPicture) {
        return false;
    }

    @Override
    public List<TemplateStockPicture> listPics(TemplateStockPicture templateStockPicture) {
        return null;
    }
}
