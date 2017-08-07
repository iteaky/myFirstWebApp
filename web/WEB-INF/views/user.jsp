<%--
  Created by IntelliJ IDEA.
  User: KOT
  Date: 25.07.17
  Time: 1:37
  To change this template use File | Settings | File Templates.
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <title>e-Shop</title>
</head>
<body>
<form action="/UserServlet">

    <table width="100%">
        <tr align="center">

            <td width="68%"><h1> Вы: ${name}</h1></td>
            <td bgcolor="#8b0000">
                <button value="exit" name="exit">Выход</button>
            </td>
            <td width="68%"><h1> В корзине: ${counter} </h1></td>

        </tr>
    </table>
    <table border="1" width="100%" bgcolor="#a9a9a9">

        <tr align="center">
            <td bgcolor="#fff8dc">
                <h2>Название
                    <button style="align-self: flex-end;" value="sortDownName" name="sortDownName">&darr;</button>
                    <button style="align-self: flex-end;" value="sortUpName" name="sortUpName">&uarr;</button>
                </h2>
            </td>
            <td bgcolor="#fff8dc">
                <h2>Цена
                    <button style="align-self: flex-end;" value="sortDownPrice" name="sortDownPrice">&darr;</button>
                    <button style="align-self: flex-end;" value="sortUpPrice" name="sortUpPrice">&uarr;</button>
                </h2>
            </td>
            <td bgcolor="#556b2f">
                <button value="cart" name="cart">Посмотреть список покупок</button>
            </td>

        </tr>
        <c:forEach var="item" items="${collectionItem}">
        <tr align="center">
            <td>${item.name}</td>
            <td>${item.price} &#8381</td>
            <td>
                <button value="${item.id}" name="add">в корзину</button>
            </td>
        </tr>
        </c:forEach>

</form>
</body>
</html>
