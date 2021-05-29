<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>

	<%--静态包含 base标签、css样式、jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>

			<%--管理员登录后的顶部菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>操作</td>
			</tr>

			<c:forEach items="${requestScope.orders}" var="order">
				<tr>
					<td>${order.createTime}</td>
					<td>${order.totalPrice}</td>
					<td>
						<c:choose>
							<c:when test="${order.status == 0}">未发货</c:when>
							<c:when test="${order.status == 1}">已发货</c:when>
							<c:when test="${order.status == 2}">已签收</c:when>
						</c:choose>
					</td>
					<td><a href="orderServlet?action=detailsOrderItem&orderId=${order.orderId}">查看详情</a></td>
					<td>
						<c:if test="${order.status == 0}">
							<a href="orderServlet?action=sendOrder&orderId=${order.orderId}">发货</a>
						</c:if>
						<c:if test="${order.status != 0}">发货</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<%--包含页脚信息--%>
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>