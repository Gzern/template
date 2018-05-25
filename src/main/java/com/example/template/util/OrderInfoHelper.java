package com.example.template.util;

import com.example.template.entity.domain.OrderInfo;

public class OrderInfoHelper {


    public static OrderInfo.Builder createBaseOrderInfo() {
        return new OrderInfo.OrderInfoBuilder(EventRecordHelper.getUserRolePO());
    }


    /*public static OrderInfo createOrderInfo() {
        return createBaseOrderInfo().build();
    }*/

    public static OrderInfo createOrderInfo(String opBehavier) {
        return createBaseOrderInfo().buildOpBehavier(opBehavier).build();
    }

    public static OrderInfo createOrderInfo(String opBehavier
            , Integer amount, String address) {
        return createBaseOrderInfo().buildOpBehavier(opBehavier)
                .buildOpAmount(amount)
                .buildOpAddress(address)
                .build();
    }


}
