<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: KOT
  Date: 27.07.17
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<form action="/UserListServlet">

    <table border="1" width="100%" bgcolor="#a9a9a9">
        <tr align="center">
            <td bgcolor="#fff8dc">Имя
                <button style="align-self: flex-end;" value="sortDown" name="sortDown">&darr;</button>
                <button style="align-self: flex-end;" value="sortUp" name="sortUp">&uarr;</button>
            </td>
            <td bgcolor="#fff8dc">Статус Оплаты</td>
            <td bgcolor="#fff8dc">Дата Последнего заказа</td>
            <td bgcolor="#fff8dc">Статус блокировки</td>
            <td bgcolor="#8b0000">
                <button value="back" name="back">Назад</button>
            </td>


        </tr>
        <c:forEach var="item" items="${collectionUser}">
            <tr align="center">
                <td>${item.name}</td>

                <td>
                    <c:choose>
                        <c:when test="${item.paymentStatus}">Оплачено</c:when>
                        <c:otherwise>Не оплачено</c:otherwise>
                    </c:choose>
                </td>
                <td>${item.paymentDate}</td>
                <td>
                    <c:choose>
                        <c:when test="${item.isBlocked}">Заблокирован</c:when>
                        <c:otherwise>Доступ разрешен</c:otherwise>
                    </c:choose></td>


                <td>
                    <button value="${item.id}" name="block">block/unblock</button>
                </td>


            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
