package com.zqx.dao;

import com.zqx.pojo.Order;

import java.util.List;

public interface OrderDao {

    public int createOrder(Order order);

    public int changeOrderStatus(String orderId,Integer status);

    public List<Order> myOrders(Integer id);

    public List<Order> allOrders();
}
