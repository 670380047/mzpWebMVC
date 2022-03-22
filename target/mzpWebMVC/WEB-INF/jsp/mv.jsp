<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/18
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%-- 没有指定jsp的编码方式pageEncoding的话，会按照页面显示编码contentType来指定jsp的编码方式。
    如果后者也没有的话，就默认iso8859-1
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>模型视图</title>
</head>
<body>
modelAndView 模型和视图
<p></p>
${model}
</body>
</html>
