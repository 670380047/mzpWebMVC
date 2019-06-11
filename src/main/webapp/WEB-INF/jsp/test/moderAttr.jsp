<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/28
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%-- 引用资源文件配置页--%>
    <%@include file="include.jsp"%>

    <title>ModelAttribute展示</title>
    <script type="text/javascript">
        $(function () {
            alert(1);
            $("#button").click(function () {
               $("#passwordId").toggle();
            });
        });
    </script>
</head>
<body>
ModelAttribute展示：==========<br>
用户名：${UserInfo123.username}<br>
<span id="passwordId">密码：${UserInfo123.password}</span>
年龄：${UserInfo123.myAge}
<p><input id="button" type="button" value="显示/隐藏"/></p>
</body>
</html>
