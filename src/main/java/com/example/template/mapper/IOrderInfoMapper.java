package com.example.template.mapper;

import com.example.template.entity.domain.OrderInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IOrderInfoMapper {

    void insertOrderInfo(OrderInfo orderInfo);

    List<OrderInfo> selectAllOrderInfosOrByExample(OrderInfo orderInfo);

}
