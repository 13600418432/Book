package com.zqx.web;

import com.zqx.pojo.Cart;
import com.zqx.pojo.Order;
import com.zqx.pojo.OrderItem;
import com.zqx.pojo.User;
import com.zqx.service.OrderService;
import com.zqx.service.impl.OrderServiceImpl;
import com.zqx.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{

    OrderService orderService = new OrderServiceImpl();

    protected void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            req.getRequestDispatcher("index.jsp").forward(req,resp);
            return;
        }
        String orderId = orderService.creatOrder(cart, user.getId());
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");

    }

    protected void myOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        List<Order> orders = orderService.myOrder(user.getId());
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    protected void allOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.allOrder();
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

    protected void detailsOrderItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = orderService.detailsOrderItem(orderId);
        req.setAttribute("orderItems",orderItems);
        req.setAttribute("url",req.getHeader("Referer"));
        req.getRequestDispatcher("pages/order/orderDetails.jsp").forward(req,resp);
    }

    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.sendOrder(orderId);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.receiveOrder(orderId);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
