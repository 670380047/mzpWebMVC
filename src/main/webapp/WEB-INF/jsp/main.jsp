<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/20
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<html>
<head>
    <title>jsp首页</title>
</head>
<body>
欢迎登陆主页

<table align="center" border="1" cellspacing="0">
    <tr>
        <td>id</td>
        <td>username</td>
        <td>password</td>
        <td>age</td>
    </tr>
    ${userInfoList}
    <%--<c:forEach var="userInfo" items="userInfoList">--%>
    <%--<tr>--%>
        <%--<td>${userInfo.get("id")}</td>--%>
        <%--<td>${userInfo.get("username")}</td>--%>
        <%--<td>${userInfo.get("password")}</td>--%>
        <%--<td>${userInfo.get("my_age")}</td>--%>
    <%--</tr>--%>
    <%--</c:forEach>--%>
</table>
</body>
</html>
