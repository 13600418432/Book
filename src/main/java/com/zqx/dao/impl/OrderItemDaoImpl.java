package com.zqx.dao.impl;

import com.zqx.dao.BaseDao;
import com.zqx.dao.OrderItemDao;
import com.zqx.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int creatOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,price,total_money,count,order_id) value(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getCount(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String OrderId) {
        String sql = "select id,`name`,price,total_money totalPrice,count,order_id orderId from t_order_item where order_id = ?";
        return queryForList(OrderItem.class,sql,OrderId);
    }
}
