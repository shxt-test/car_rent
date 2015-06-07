<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title> | </title>
    <script type="text/javascript">
    	
    </script>
  </head>
  
  <body>
  <div style="height: 800px;width: 1000px;">
   <h1>www.ccshxt.com</h1><br>
   <s:debug></s:debug>
  </div>
  
  </body>
</html>
