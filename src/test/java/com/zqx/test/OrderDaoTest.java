package com.zqx.test;

import com.zqx.dao.OrderDao;
import com.zqx.dao.impl.OrderDaoImpl;
import com.zqx.pojo.Order;
import com.zqx.utils.JdbcUtils;
import org.junit.After;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {

    OrderDao orderDao = new OrderDaoImpl();

    @After
    public void tearDown() throws Exception {
        JdbcUtils.commitAndClose();
    }

    @Test
    public void createOrder() {
        Order order = new Order("1234",new Date(new java.util.Date().getTime()),new BigDecimal(22),0,1);
        orderDao.createOrder(order);
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus("1234",2);
    }

    @Test
    public void myOrders() {
        List<Order> orders = orderDao.myOrders(1);
        System.out.println(orders);
    }

    @Test
    public void allOrders() {
        List<Order> orders = orderDao.allOrders();
        System.out.println(orders);
    }
}