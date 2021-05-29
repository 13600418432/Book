package com.zqx.dao.impl;

import com.zqx.dao.BaseDao;
import com.zqx.dao.OrderDao;
import com.zqx.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int createOrder(Order order) {
        String sql = "insert into t_order(order_id,create_time,price,`status`,user_id) value(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getTotalPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public int changeOrderStatus(String orderId, Integer status) {
        String sql = "update t_order set `status` = ? where order_id = ?";
        return update(sql,status,orderId);
    }

    @Override
    public List<Order> myOrders(Integer id) {
        String sql = "select order_id orderId,create_time createTime,price totalPrice,`status`,user_id userId from t_order where user_id = ?";
        return queryForList(Order.class,sql,id);
    }

    @Override
    public List<Order> allOrders() {
        String sql = "select order_id orderId,create_time createTime,price totalPrice,`status`,user_id userId from t_order";
        return queryForList(Order.class,sql);
    }
}
