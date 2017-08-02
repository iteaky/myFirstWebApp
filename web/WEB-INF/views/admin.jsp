<%--
  Created by IntelliJ IDEA.
  User: KOT
  Date: 27.07.17
  Time: 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="#a9a9a9">
<form action="/adminPage">
    <div style="height: 25%"></div>

    <h1 align="center" >Вы администратор!</h1>
    <table align="center">
        <td>
            <button value="user" name="user">клиенты</button>
        </td>
        <td>
            <button value="item" name="item">товары</button>
        </td>
    </table>
    <table align="center">
        <tr bgcolor="#8b0000" align="center">
            <td>
                <button value="exit" name="exit">выход</button>
            </td>
        </tr>
    </table>

</form>

</body>
</html>
