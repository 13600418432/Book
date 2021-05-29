package com.zqx.dao;

import com.zqx.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {

    public int creatOrderItem(OrderItem orderItem);

    public List<OrderItem> queryOrderItemByOrderId(String OrderId);

}
