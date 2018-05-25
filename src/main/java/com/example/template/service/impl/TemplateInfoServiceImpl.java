package com.example.template.service.impl;

import com.example.template.entity.domain.TemplateInfo;
import com.example.template.entity.domain.TemplateStock;
import com.example.template.entity.domain.TemplateStockPicture;
import com.example.template.mapper.ITemplateInfoMapper;
import com.example.template.mapper.ITemplateStockMapper;
import com.example.template.mapper.ITemplateStockPicMapper;
import com.example.template.service.ITemplateInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TemplateInfoServiceImpl implements ITemplateInfoService {

    private Logger logger = LoggerFactory.getLogger(TemplateInfoServiceImpl.class);

    @Autowired
    private ITemplateInfoMapper iTemplateInfoMapper;

    @Autowired
    private ITemplateStockMapper iTemplateStockMapper;

    @Autowired
    private ITemplateStockPicMapper iTemplateStockPicMapper;

    @Override
    public boolean addTemplateInfo(TemplateInfo templateInfo) {
        if (existed(templateInfo)) {
            return false;
        } else {
            try {
                iTemplateInfoMapper.addTemplate(templateInfo);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }

    @Override
    public boolean deleteTemplateInfo(TemplateInfo templateInfo) throws IOException {
        logger.info("报废样板信息,{}", templateInfo);
        List<TemplateStock> templateStock = iTemplateStockMapper
                .findTemplateStockByTemplateId(templateInfo.getTemplateId());
        if (templateStock != null && !templateStock.isEmpty()) {
            logger.info("delete stockInfo,{}", templateStock);
            for (TemplateStock stock : templateStock) {
                Long stockId = stock.getId();
                TemplateStockPicture picture = new TemplateStockPicture();
                picture.setStockId(stockId);
                List<TemplateStockPicture> templateStockPictures = iTemplateStockPicMapper.listPics(picture);
                if (templateStockPictures != null && !templateStockPictures.isEmpty()) {
                    logger.info("delete stockPic,{}", templateStockPictures);
                    for (TemplateStockPicture stockPicture : templateStockPictures) {
                        FileUtils.forceDelete(new File(stockPicture.getPictureUrl()));
                    }
                }
                iTemplateStockPicMapper.deletePicByStockId(stockId);
            }
        }
        iTemplateStockMapper.deleteTemplateStockByTemplateId(templateInfo.getTemplateId());
        iTemplateInfoMapper.deleteTemplateByTemplateId(templateInfo);
        logger.info("delete templateInfo finished");
        return true;
    }

    @Override
    public boolean updateTemplateInfo(TemplateInfo templateInfo) {
        return false;
    }

    @Override
    public Map<String, Object> listTemplateInfos(String templateId, int startPage, int pageSize) {
        PageHelper.startPage(startPage, pageSize);
        List<TemplateInfo> templateInfos = iTemplateInfoMapper.listAllTemplatesOrByTemplateId(templateId);
        PageInfo<TemplateInfo> pageInfo = new PageInfo<>(templateInfos);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", pageInfo.getTotal());
        map.put("templateInfoList", pageInfo.getList());
        return map;
    }

    @Override
    public boolean existed(TemplateInfo templateInfo) {
        TemplateInfo template = iTemplateInfoMapper.findTemplateByTemplateInfoId(templateInfo);
        logger.info("样板信息是否存在,{}", template);
        if (template != null && StringUtils.equals(template.getTemplateId(), templateInfo.getTemplateId())) {
            return true;
        }
        return false;
    }

    @Override
    public TemplateInfo findTemplateInfoByTemplateId(String templateId) {
        TemplateInfo templateInfo = new TemplateInfo();
        templateInfo.setTemplateId(templateId);
        return iTemplateInfoMapper.findTemplateByTemplateInfoId(templateInfo);
    }
}
