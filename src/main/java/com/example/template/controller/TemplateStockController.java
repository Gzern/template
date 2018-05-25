package com.example.template.controller;

import com.example.template.entity.domain.OrderInfo;
import com.example.template.entity.domain.TemplateStock;
import com.example.template.entity.domain.TemplateStockPicture;
import com.example.template.entity.pojo.ResponseResult;
import com.example.template.entity.pojo.Result;
import com.example.template.mapper.IOrderInfoMapper;
import com.example.template.service.ITemplateStockService;
import com.example.template.util.EventRecordHelper;
import com.example.template.util.ImageUtils;
import com.example.template.util.OrderInfoHelper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@RestController
@RequestMapping("/templateStock")
public class TemplateStockController {

    @Value("${web.upload.path}")
    private String uploadPath;

    private Logger logger = LoggerFactory.getLogger(TemplateStockController.class);

    @Autowired
    private ITemplateStockService iTemplateStockService;
    @Autowired
    private IOrderInfoMapper iOrderInfoMapper;

    @PostMapping("/add")
    @Transactional
    public String addTemplateStock(TemplateStock templateStock, @RequestParam("images[]") MultipartFile[] files) {
        logger.info("开始添加样板库存,{}", templateStock);
        List<TemplateStockPicture> images = new LinkedList<>();
        if (files != null) {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename().trim().toLowerCase();
                if (!ImageUtils.isImageFile(fileName)) continue;
                TemplateStockPicture templateStockPicture = new TemplateStockPicture();
                templateStockPicture.setPictureCtime(new Date());
                String path = uploadPath
                        + templateStock.getTemplateId() + "_" + templateStock.getTemplateStore() + "/"
                        + UUID.randomUUID().toString() + ImageUtils.getFileType(fileName);
                templateStockPicture.setPictureUrl(path);
                File outFile = new File(path);
                try {
                    FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
                    images.add(templateStockPicture);
                } catch (IOException io) {
                    logger.info("上传图片失败,{}", file);
                    return Result.ERROR.getMsg();
                }
            }
        }
        if (iTemplateStockService.addTemplateStock(templateStock, images)) {
            logger.debug("添加样板库存结束");
            /*UserRolePO userRolePO = EventRecordHelper.getUserRolePO();
            OrderInfo orderInfo = new OrderInfo.OrderInfoBuilder(userRolePO)
                    .buildOpBehavier("添加库存")
                    .buildOpAmount(templateStock.getTemplateAmount())
                    .buildOpAddress("存放点:"
                            + templateStock.getTemplateAreas()
                            + "货架编号:" + templateStock.getTemplateStore())
                    .build();*/
            OrderInfo orderInfo = OrderInfoHelper.createOrderInfo("添加库存"
                    , templateStock.getTemplateAmount()
                    , "存放点:"
                            + templateStock.getTemplateAreas()
                            + "货架编号:" + templateStock.getTemplateStore());
            orderInfo.setOpFactory(templateStock.getTemplateFactory());
            iOrderInfoMapper.insertOrderInfo(orderInfo);
            return Result.OK.getMsg();
        } else {
            logger.info("添加样板库存结束");
            return Result.ERROR.getMsg();
        }
    }

    @PostMapping("/in")
    @Transactional
    public ResponseResult<TemplateStock> out2in(@RequestBody TemplateStock templateStock) {
        logger.info("样板调入,{}", templateStock);
        ResponseResult<TemplateStock> responseResult = iTemplateStockService.updateTemplateStock(templateStock);
        if ("SUCCESS".equals(responseResult.getSuccess())) {
            OrderInfo orderInfo = OrderInfoHelper.createOrderInfo("样板调入"
                    , templateStock.getTemplateAmount()
                    , "存放点:"
                            + templateStock.getTemplateAreas()
                            + "货架编号:" + templateStock.getTemplateStore());
            orderInfo.setOpFactory(responseResult.getData().getTemplateFactory());
            iOrderInfoMapper.insertOrderInfo(orderInfo);
        }
        return responseResult;
    }

    @PostMapping("/out")
    @Transactional
    public ResponseResult<TemplateStock> in2out(@RequestBody TemplateStock templateStock) {
        templateStock.setTemplateAmount(templateStock.getTemplateAmount() * -1);
        logger.info("样板调出,{}", templateStock);
        ResponseResult<TemplateStock> responseResult = iTemplateStockService.updateTemplateStock(templateStock);
        if ("SUCCESS".equals(responseResult.getSuccess())) {
            OrderInfo orderInfo = OrderInfoHelper.createOrderInfo("样板调出"
                    , templateStock.getTemplateAmount() * -1
                    , "存放点:"
                            + templateStock.getTemplateAreas()
                            + "货架编号:" + templateStock.getTemplateStore());
            orderInfo.setOpFactory(responseResult.getData().getTemplateFactory());
            iOrderInfoMapper.insertOrderInfo(orderInfo);
        }
        return responseResult;
    }

    @PostMapping("/lend")
    @Transactional
    public ResponseResult<TemplateStock> borrow(@RequestBody TemplateStock templateStock) {
        templateStock.setTemplateAmount(templateStock.getTemplateAmount() * -1);
        templateStock.setTemplateBorrowed(true);
        logger.info("样板外借,{}", templateStock);
        ResponseResult<TemplateStock> responseResult = iTemplateStockService.updateTemplateStock(templateStock);
        if ("SUCCESS".equals(responseResult.getSuccess())) {
            OrderInfo orderInfo = OrderInfoHelper.createOrderInfo("样板外借"
                    , templateStock.getTemplateAmount() * -1
                    , "外借存放点:"
                            + templateStock.getTemplateAreas()
                            + "外借货架编号:" + templateStock.getTemplateStore());
            orderInfo.setOpFactory(responseResult.getData().getTemplateFactory());
            iOrderInfoMapper.insertOrderInfo(orderInfo);
        }
        return responseResult;
    }

    @RequestMapping("/list")
    public Map<String, Object> listTemplateStockInfo(
            TemplateStock templateStock,
            @RequestParam(value = "startPage", defaultValue = "1") int startPage,
            @RequestParam(value = "pageSize", defaultValue = "100") int pageSize) {
        logger.info("展示样板库存信息，查询条件为，{}", templateStock);
        if (templateStock == null) templateStock = new TemplateStock();
        /**
         * 判断当前用户是否是分厂管理员
         * 如果是则分厂管理员则设置分厂属性,只能查询该分厂所属的订单
         */
        String userFactory = EventRecordHelper.getUserRolePO().getUserFactory();
        if (StringUtils.isNotBlank(userFactory)) {
            templateStock.setTemplateFactory(userFactory);
        }
        Map<String, Object> map = iTemplateStockService.listTemplateStockByExample(templateStock, startPage, pageSize);
        return map;
    }

    @RequestMapping("/count")
    public String countTemplateStockInfoNum() {
        return iTemplateStockService.countTemplateStockInfoNum() + "";
    }

    @RequestMapping("/stockInfo")
    public ResponseResult<TemplateStock> getStock(Long id) {
        ResponseResult<TemplateStock> responseResult = new ResponseResult<>();
        TemplateStock stockById = iTemplateStockService.getTemplateStockById(id);
        responseResult.setSuccess(Result.SUCCESS.getMsg());
        responseResult.setData(stockById);
        return responseResult;
    }

    @RequestMapping("/delete")
    @Transactional
    public ResponseResult deleteStock(Long templateStockId) {
        logger.info("delete StockInfo , id = {}", templateStockId);
        TemplateStock stockById = iTemplateStockService.getTemplateStockById(templateStockId);
        iTemplateStockService.deleteTemplateStockByTemplateStockId(templateStockId);
        OrderInfo orderInfo = OrderInfoHelper.createOrderInfo("删除库存信息", null
                , "存放点:"
                        + stockById.getTemplateAreas()
                        + "货架编号:" + stockById.getTemplateStore());
        orderInfo.setOpFactory(stockById.getTemplateFactory());
        iOrderInfoMapper.insertOrderInfo(orderInfo);
        return new ResponseResult(Result.SUCCESS);
    }

    @GetMapping(value = "/download")
    public void download(TemplateStock templateStock, HttpServletResponse response) throws IOException {
        Optional<TemplateStock> op = Optional.ofNullable(templateStock);
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename="
                + URLEncoder.encode(new String(
                        "样板库存信息.xls".getBytes("utf-8"), "iso8859-1")
                , "iso8859-1"));
        Workbook workbook = new HSSFWorkbook();
        iTemplateStockService.downloadTemplateInfosByExample(workbook, op.orElse(new TemplateStock()));
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
    }
}
