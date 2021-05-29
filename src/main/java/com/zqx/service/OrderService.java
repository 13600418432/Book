package com.zqx.service;

import com.zqx.pojo.Cart;
import com.zqx.pojo.Order;
import com.zqx.pojo.OrderItem;

import java.util.List;

public interface OrderService {

    public String creatOrder(Cart cart,Integer userId);

    public List<Order> myOrder(Integer userId);

    public List<Order> allOrder();

    public List<OrderItem> detailsOrderItem(String OrderId);

    public void sendOrder(String OrderId);

    public void receiveOrder(String OrderId);
}
