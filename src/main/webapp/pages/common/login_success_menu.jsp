<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MI
  Date: 2021/5/28
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <c:if test="${not empty sessionScope.user.username}">
        <a href="userServlet?action=logout">注销</a>
    </c:if>
    <a href="orderServlet?action=myOrder">我的订单</a>
    <a href="index.jsp">返回</a>
</div>
