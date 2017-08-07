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
    <title>Items</title>
</head>
<body >
<form action="/ItemListServlet">


    <table width="100%" border="1" bgcolor="#a9a9a9" style="border-radius: 5px">

        <td  align="center" bgcolor="#fff8dc">Наименование
            <button style="align-self: flex-end;" value="sortDownName" name="sortDownName">&darr;</button>
            <button style="align-self: flex-end;" value="sortUpName" name="sortUpName">&uarr;</button>
        </td>
        <td align="center" bgcolor="#fff8dc">Цена
            <button style="align-self: flex-end;" value="sortDownPrice" name="sortDownPrice">&darr;</button>
            <button style="align-self: flex-end;" value="sortUpPrice" name="sortUpPrice">&uarr;</button>
        </td>
        <td  align="center" bgcolor="#fff8dc">новое название</td>
        <td align="center" bgcolor="#fff8dc">новая цена</td>
        <td  align="center" bgcolor="#fff8dc"></td>
        <td align="center" bgcolor="#8b0000">
            <button value="back" name="back">Назад</button>
        </td>


        <c:forEach var="item" items="${collectionItem}">
            <tr>

                <td align="center">${item.name}</td>
                <td align="center">${item.price}</td>
                <td align="center"><h5 align="center"><input name="name${item.id}"></h5>
                <td align="center"><h5 align="center"><input type="number" name="price${item.id}"></h5>
                <td align="center">
                    <button value="${item.id}" name="change">изменить</button>
                </td>
                <td align="center">
                    <button value="${item.id}" name="delete">удалить</button>
                </td>
            </tr>
        </c:forEach>
        <td align="center"><h5 align="center"><input name="newName"></h5></td>
        <td align="center"><h5 align="center"><input name="newPrice" type="number"></h5></td>
        <td align="center">
            <button value="addSmth" name="add">Добавить</button>
        </td>

    </table>
</form>
</body>
</html>
