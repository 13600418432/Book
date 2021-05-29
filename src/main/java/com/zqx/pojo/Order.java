package com.zqx.pojo;

import java.math.BigDecimal;
import java.sql.Date;

public class Order {
    private String orderId;
    private Date createTime;
    private BigDecimal totalPrice;
    private Integer status;  //表示状态，0表示未发货，1表示已发货，2表示已签收
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date createTime, BigDecimal totalPrice, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.totalPrice = totalPrice;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
