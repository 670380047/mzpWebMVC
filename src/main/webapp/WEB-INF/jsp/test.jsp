<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/16
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
    <%-- 引用资源文件配置页--%>
    <%@include file="test/include.jsp"%>
</head>
<body>
    <script>
       function mzpTest() {
           var urlName = "urlNameTest";
           var url = "queryTest?urlName="+urlName;
           var obj = {"username":"mzp","password":"123","myAge":24}

           $.ajax({
               url:url,
               type:'POST',
               dataType:'JSON',
               data:JSON.stringify(obj),
               contentType:'application/json;charset=utf-8',
               success:function (result) {
                   alert(JSON.stringify(result))
                   alert("请求成功")
               },
               error:function (msg) {
                   // alert(msg)
                   alert("请求失败")
               }
           });
       }
    </script>
    <div>
        测试数据：<br/>
        <input type="button" value="提交" onclick="mzpTest()">
    </div>
</body>
</html>
