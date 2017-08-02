<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: KOT
  Date: 30.07.17
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Корзина</title>
</head>
<body>
<form action="/BinServlet">
    <table width="100%">
        <tr align="center">
            <td><h2>Корзина пользователя ${name} </h2></td>
            <td bgcolor="#e9967a">
                <button value="back" name="back">Назад</button>
            </td>
            <td bgcolor="#8b0000">
                <button value="exit" name="exit">Выход</button>
            </td>
        </tr>

    </table>
    <table border="1" bgcolor="#a9a9a9" width="100%">
        <tr align="center">
            <td bgcolor="#fff8dc"><h2>Название</h2></td>
            <td bgcolor="#fff8dc"><h2>Цена</h2></td>
            <td bgcolor="#fff8dc"><h2>Удалить</h2></td>
        </tr>
        <c:forEach var="item" items="${userBin}">
            <tr align="center">
                <td align="center">${item.name}</td>
                <td align="center">${item.price}</td>
                <td align="center">
                    <button value="${item.name}" name="delete">удалить</button>
                </td>
            </tr>
        </c:forEach>
        <tr align="center">
            <td bgcolor="#fff8dc"><h3>Количество товаров: ${counter}</h3></td>
            <td bgcolor="#fff8dc"><h3>Общая стоимость: ${binPrice}</h3></td>
            <td bgcolor="#556b2f">
                <button value="bin" name="bin">Оплатить</button>
            </td>


        </tr>
    </table>
</form>
</body>
</html>
