package com.zqx.service.impl;

import com.zqx.dao.BookDao;
import com.zqx.dao.OrderDao;
import com.zqx.dao.OrderItemDao;
import com.zqx.dao.impl.BookDaoImpl;
import com.zqx.dao.impl.OrderDaoImpl;
import com.zqx.dao.impl.OrderItemDaoImpl;
import com.zqx.pojo.*;
import com.zqx.service.OrderService;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    @Override
    public String creatOrder(Cart cart,Integer userId) {
        String orderId = System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId,new Date(new java.util.Date().getTime()),cart.getTotalPrice(),0,userId);
        orderDao.createOrder(order);
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getPrice(),cartItem.getPriceTotal(),cartItem.getCount(),orderId);
            orderItemDao.creatOrderItem(orderItem);

            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> myOrder(Integer userId) {
        List<Order> orders = orderDao.myOrders(userId);
        return orders;
    }

    @Override
    public List<Order> allOrder() {
        List<Order> orders = orderDao.allOrders();
        return orders;
    }

    @Override
    public List<OrderItem> detailsOrderItem(String OrderId) {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId(OrderId);
        return orderItems;
    }

    @Override
    public void sendOrder(String OrderId) {
        orderDao.changeOrderStatus(OrderId,1);
    }

    @Override
    public void receiveOrder(String OrderId) {
        orderDao.changeOrderStatus(OrderId,2);
    }
}
