<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>汽车租赁管理系统*****DSADASDSADSADSADASDASD123</title>
  </head>
  <body>
  
       <%
        request.getRequestDispatcher("/jsp/login.jsp").forward(request,response); 
        %>
  </body>
</html>
