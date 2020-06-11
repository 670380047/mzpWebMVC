<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/18
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<html>
<head>
    <title>登陆</title>
    <%-- 引用资源文件配置页--%>
    <%@include file="test/include.jsp"%>
</head>
<body>
<script type="text/javascript">
    document.write("<h1>This is a heading</h1>");
    document.write("<p>This is a paragraph.</p>");
</script>
<form action="welcome" method="post">
    用户名123：<input type="text" name="username"><br/>
    密码：<input type="password" name="password"> <br/> <br/>
    <input type="submit" name="登陆" style="margin-left:65px;"  >
    <p>
        ${sessionScope.get("LoginMessageMap").get("username")}
    </p>
    <p>${message}</p>
    <%--<a href="test.jsp">注册测试 </a>--%>
</form>
</body>
</html>
