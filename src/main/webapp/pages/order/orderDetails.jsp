<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>

    <%--静态包含 base标签、css样式、jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">订单详情</span>

    <div>
        <span>订单号：${param.orderId}</span>
        <a href="${requestScope.url}">返回</a>
        <a href="index.jsp">返回商城</a>
    </div>

</div>

<div id="main">


    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
        </tr>

        <c:if test="${empty requestScope.orderItems}">
            <tr>
                <td colspan="5"><a href="index.jsp">当前订单为空，快去浏览商品吧</a></td>
            </tr>
        </c:if>

        <c:if test="${not empty requestScope.orderItems}">
            <c:forEach items="${requestScope.orderItems}" var="orderItem">
                <tr>
                    <td>${orderItem.name}</td>
                    <td>${orderItem.count}</td>
                    <td>${orderItem.price}</td>
                    <td>${orderItem.totalPrice}</td>
                </tr>
            </c:forEach>
        </c:if>

    </table>
</div>

<%--包含页脚信息--%>
<%@include file="/pages/common/footer.jsp" %>

</body>
</html>