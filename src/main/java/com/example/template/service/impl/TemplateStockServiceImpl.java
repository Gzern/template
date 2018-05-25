package com.example.template.service.impl;

import com.example.template.entity.domain.TemplateInfo;
import com.example.template.entity.domain.TemplateStock;
import com.example.template.entity.domain.TemplateStockPicture;
import com.example.template.entity.pojo.ResponseResult;
import com.example.template.entity.pojo.Result;
import com.example.template.entity.pojo.UserRolePO;
import com.example.template.entity.vo.TemplateStockVO;
import com.example.template.mapper.ITemplateInfoMapper;
import com.example.template.mapper.ITemplateStockMapper;
import com.example.template.mapper.ITemplateStockPicMapper;
import com.example.template.service.ITemplateStockService;
import com.example.template.util.EventRecordHelper;
import com.example.template.util.ExcelUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class TemplateStockServiceImpl implements ITemplateStockService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ITemplateStockMapper iTemplateStockMapper;

    @Autowired
    private ITemplateInfoMapper iTemplateInfoMapper;

    @Autowired
    private ITemplateStockPicMapper iTemplateStockPicMapper;

    @Override
    public boolean addTemplateStock(TemplateStock templateStock, List<TemplateStockPicture> pictures) {
        logger.info("添加库存信息,{}", templateStock);
        if (existedTemplateStock(templateStock)) {
            logger.info("stock has existed");
            return false;
        }
        UserRolePO userRolePO = EventRecordHelper.getUserRolePO();
        if (StringUtils.isNotBlank(userRolePO.getUserFactory())
                && !StringUtils.equalsIgnoreCase(templateStock.getTemplateFactory(), userRolePO.getUserFactory())) {
            logger.info("this user is factorier , but the factory is not match");
            return false;
        }
        templateStock.setTemplateCtime(new Date());
        TemplateInfo templateInfo = new TemplateInfo();
        templateInfo.setTemplateId(templateStock.getTemplateId());
        TemplateInfo template = iTemplateInfoMapper.findTemplateByTemplateInfoId(templateInfo);
        double d = (double) templateStock.getTemplateAmount() / template.getPiecesPerSuit();
        templateStock.setTemplateSuits(Double.parseDouble(String.format("%.2f", d)));
        iTemplateStockMapper.addTemplateStock(templateStock);
        TemplateStock templateStock1 = iTemplateStockMapper.findTemplateStock(templateStock);
        logger.info("stock picture,{}", pictures);
        for (TemplateStockPicture picture : pictures) {
            picture.setStockId(templateStock1.getId());
            iTemplateStockPicMapper.addPic(picture);
        }
        logger.info("add finished");
        return true;
    }

    @Override
    public ResponseResult<TemplateStock> updateTemplateStock(TemplateStock templateStock) {
        TemplateStock stockById = iTemplateStockMapper.findTemplateStockById(templateStock.getId());
        int amount = stockById.getTemplateAmount() + templateStock.getTemplateAmount();
        if (amount < 0) return new ResponseResult<>(Result.FAILED, stockById);
        TemplateInfo templateInfo = new TemplateInfo();
        templateInfo.setTemplateId(templateStock.getTemplateId());
        TemplateInfo template = iTemplateInfoMapper.findTemplateByTemplateInfoId(templateInfo);
        stockById.setTemplateAmount(amount);
        double d = (double) templateStock.getTemplateAmount() / template.getPiecesPerSuit();
        stockById.setTemplateSuits(Double.parseDouble(String.format("%.2f", d)));
        stockById.setTemplateBorrowed(templateStock.getTemplateBorrowed());
        iTemplateStockMapper.updateTemplateStock(stockById);
        return new ResponseResult<>(Result.SUCCESS, stockById);
    }

    @Override
    public boolean deleteTemplateStockByTemplateStockId(Long templateStockId) {
        iTemplateStockPicMapper.deletePicByStockId(templateStockId);
        iTemplateStockMapper.deleteTemplateStockByStockId(templateStockId);
        return true;
    }

    @Override
    public boolean existedTemplateStock(TemplateStock templateStock) {
        TemplateStock templateStock1 = iTemplateStockMapper.findTemplateStock(templateStock);
        if (templateStock1 != null) return true;
        return false;
    }

    @Override
    public Map<String, Object> listTemplateStockByExample(TemplateStock templateStock, int startNum, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        List<TemplateStockVO> tsvos = new LinkedList<>();
        UserRolePO userRolePO = EventRecordHelper.getUserRolePO();
        if (StringUtils.isNotBlank(userRolePO.getUserFactory())) {
            templateStock.setTemplateFactory(userRolePO.getUserFactory());
        } else {
            Optional<String> op = Optional.ofNullable(templateStock.getTemplateFactory());
            templateStock.setTemplateFactory("%" + op.orElse("") + "%");
        }
        PageHelper.startPage(startNum, pageSize);
        PageInfo<TemplateStock> pageInfo =
                new PageInfo<>(iTemplateStockMapper.listTemplateStocksByExample(templateStock));
        List<TemplateStock> templateStocks = pageInfo.getList();
        map.put("totalCount", pageInfo.getTotal());
        if (templateStocks == null || templateStocks.isEmpty()) {
            map.put("templateStockList", tsvos);
            return map;
        }
        for (TemplateStock stock : templateStocks) {
            TemplateStockVO templateStockVO = new TemplateStockVO();
            templateStockVO.setTemplateStock(stock);
            TemplateStockPicture picture = new TemplateStockPicture();
            picture.setStockId(stock.getId());
            List<TemplateStockPicture> pictures = iTemplateStockPicMapper.listPics(picture);
            if (picture != null && !pictures.isEmpty()) {
                for (TemplateStockPicture stockPicture : pictures) {
                    String url = stockPicture.getPictureUrl();
                    url = url.substring(url.indexOf("/"));
                    stockPicture.setPictureUrl(url);
                }
            }
            templateStockVO.setImages(pictures);
            tsvos.add(templateStockVO);
        }
        map.put("templateStockList", tsvos);
        return map;
    }

    @Override
    public int countTemplateStockInfoNum() {
        return iTemplateStockMapper.countTemplateStockInfoNum();
    }

    @Override
    public TemplateStock getTemplateStockById(Long id) {
        TemplateStock stockById = iTemplateStockMapper.findTemplateStockById(id);
        return stockById;
    }

    @Override
    public void downloadTemplateInfosByExample(Workbook workbook, TemplateStock templateStock) {
        Map<String, Object> map = this.listTemplateStockByExample(templateStock, 1, 0);
        List<TemplateStockVO> tsvos = (List<TemplateStockVO>) map.get("templateStockList");
        ExcelUtils.list2excel(workbook, tsvos);
    }
}
