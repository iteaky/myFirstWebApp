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

<form action="/welcomePage">
    <div style="height: 25%"></div>
    <h1 align="center">Hello!</h1>
    <h2 align="center">name:<input required name="user"></h2>
    <h2 align="center">
        <td>admin</td>
        <input type="checkbox"  name="Администратор" value="true"></h2>
    <h2 align="center"><input type="submit" value="Отправить"></h2>

</form>
</body>
</html>
