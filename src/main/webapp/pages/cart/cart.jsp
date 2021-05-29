<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>

    <%--静态包含 base标签、css样式、jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>

    <script type="text/javascript">
        $(function () {

            <%--给商品数量输入框绑定一个修改时间--%>
           $("input.updateCount").change(function () {
               let count = $(this).val()
               if (confirm("是否将【" +
                   $(this).parent().parent().find("td:first").text()
                   + "】商品的数量修改为"+count)){
                   let id = $(this).attr("bookId");
                    location.href = "${pageScope.basePath}cartServlet?action=updateCount&id="+id+"&count="+count;
               }
               else {
                   this.value = this.defaultValue;
               }
           });

           <%--給删除绑定一个点击事件--%>
           $("a#deleteItem").click(function () {
               return confirm("是否要将【"+$(this).parent().parent().find("td:first").text()+"】删除");
           });

           <%--給清空购物车绑定一个点击事件--%>
            $("a#clearCart").click(function () {
                return confirm("是否要清空购物车");
            });
        });
    </script>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>

    <%--用户登录成功后的顶部菜单--%>
    <%@include file="/pages/common/login_success_menu.jsp" %>

</div>

<div id="main">


    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>

        <c:if test="${empty sessionScope.cart.items}">
            <tr>
                <td colspan="5"><a href="index.jsp">当前购物车为空，快去浏览商品吧</a></td>
            </tr>
        </c:if>

        <c:if test="${not empty sessionScope.cart.items}">
            <c:forEach items="${sessionScope.cart.items}" var="entry">
                <tr>
                    <td>${entry.value.name}</td>
                    <td>
                        <input class="updateCount" bookId="${entry.value.id}"
                               type="text" value="${entry.value.count}" style="width:80px ">
                    </td>
                    <td>${entry.value.price}</td>
                    <td>${entry.value.priceTotal}</td>
                    <td><a href="cartServlet?action=deleteItem&id=${entry.value.id}" id="deleteItem">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>

    </table>

    <div class="cart_info">
        <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
        <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
        <c:if test="${not empty sessionScope.cart.items}">
            <span class="cart_span"><a href="cartServlet?action=clear" id="clearCart">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=create">去结账</a></span>
        </c:if>
    </div>

</div>

<%--包含页脚信息--%>
<%@include file="/pages/common/footer.jsp" %>

</body>
</html>