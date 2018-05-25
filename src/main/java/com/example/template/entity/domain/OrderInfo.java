package com.example.template.entity.domain;

import com.example.template.entity.pojo.UserRolePO;

import java.io.Serializable;
import java.util.Date;

public class OrderInfo implements Serializable {

    private Integer id;
    private Integer opUserId;
    private String opName;
    private String opRole;
    private String opFactory;
    private String opBehavier;
    private Integer opAmount;
    private String opAddress;
    private Date opTime = new Date();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(Integer opUserId) {
        this.opUserId = opUserId;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public String getOpRole() {
        return opRole;
    }

    public void setOpRole(String opRole) {
        this.opRole = opRole;
    }

    public String getOpBehavier() {
        return opBehavier;
    }

    public void setOpBehavier(String opBehavier) {
        this.opBehavier = opBehavier;
    }

    public Integer getOpAmount() {
        return opAmount;
    }

    public void setOpAmount(Integer opAmount) {
        this.opAmount = opAmount;
    }

    public String getOpAddress() {
        return opAddress;
    }

    public void setOpAddress(String opAddress) {
        this.opAddress = opAddress;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getOpFactory() {
        return opFactory;
    }

    public void setOpFactory(String opFactory) {
        this.opFactory = opFactory;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", opUserId=" + opUserId +
                ", opName='" + opName + '\'' +
                ", opRole='" + opRole + '\'' +
                ", opFactory='" + opFactory + '\'' +
                ", opBehavier='" + opBehavier + '\'' +
                ", opAmount=" + opAmount +
                ", opAddress='" + opAddress + '\'' +
                ", opTime=" + opTime +
                '}';
    }

    public static abstract class Builder {

        public abstract Builder buildOpUserId(Integer opUserId);

        public abstract Builder buildOpName(String opName);

        public abstract Builder buildOpRole(String opRole);

        public abstract Builder buildOpBehavier(String opBehavier);

        public abstract Builder buildOpAmount(Integer opAmount);

        public abstract Builder buildOpAddress(String opAddress);

        public abstract Builder buildOpTime(Date opTime);

        public abstract Builder buildOpFactory(String factory);

        public abstract OrderInfo build();
    }

    public static class OrderInfoBuilder extends Builder {

        OrderInfo orderInfo = new OrderInfo();

        public OrderInfoBuilder() {
        }

        public OrderInfoBuilder(UserRolePO userRolePO) {
            init(userRolePO);
        }

        /**
         * 初始化操作人基本信息
         *
         * @param userRolePO
         */
        private void init(UserRolePO userRolePO) {
            this.buildOpUserId(userRolePO.getUserId())
                    .buildOpName(userRolePO.getUserName())
                    .buildOpRole(userRolePO.getRoleName())
                    .buildOpFactory(userRolePO.getUserFactory());
        }

        @Override
        public Builder buildOpUserId(Integer opUserId) {
            orderInfo.setOpUserId(opUserId);
            return this;
        }

        @Override
        public Builder buildOpName(String opName) {
            orderInfo.setOpName(opName);
            return this;
        }

        @Override
        public Builder buildOpRole(String opRole) {
            orderInfo.setOpRole(opRole);
            return this;
        }

        @Override
        public Builder buildOpBehavier(String opBehavier) {
            orderInfo.setOpBehavier(opBehavier);
            return this;
        }

        @Override
        public Builder buildOpAmount(Integer opAmount) {
            orderInfo.setOpAmount(opAmount);
            return this;
        }

        @Override
        public Builder buildOpAddress(String opAddress) {
            orderInfo.setOpAddress(opAddress);
            return this;
        }

        @Override
        public Builder buildOpTime(Date opTime) {
            orderInfo.setOpTime(opTime);
            return this;
        }

        @Override
        public Builder buildOpFactory(String factory) {
            orderInfo.setOpFactory(factory);
            return this;
        }

        @Override
        public OrderInfo build() {
            return orderInfo;
        }
    }

}
