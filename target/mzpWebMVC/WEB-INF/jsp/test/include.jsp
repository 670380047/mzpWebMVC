<%--
  Created by IntelliJ IDEA.
  User: mzp
  Date: 2019/6/11
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath()+"/";
    request.setAttribute("home",contextPath);
//    System.out.println("项目名:"+contextPath);
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    System.out.println("工程路径："+basePath);
%>
    <%-- <%=contextPath%>  该形式是调用java代码 --%>
    <%-- ${home} 该形式是使用jsp内置的request。 request.setAttribute("home",contextPath);  配置了一个home --%>
<script type="text/javascript" src="${home}js/easyUI/jquery.min.js"></script>
<script type="text/javascript" src="${home}js/easyUI/jquery.easyui.min.js"></script>
