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
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
    <title> | </title>
    <script type="text/javascript">
    
    function toSub(){
    	$.post("sys/updateRoleUserAction.action",$("#userForm").serialize(),function(data){
    		if(data.flag=="success"){
                         var dialog = top.dialog.get(window);
                          dialog.close(data.flag);
                          dialog.remove();
                }else{
                    alert(data.message);
                    return false;
                }
    		
    	});
    }
    	
    </script>
  </head>
  
  <body>
   <div class="mformbody">
   <form  method="post" id="userForm">

   <h1>您正在对<font color="red"><s:property value="user.user_name"/></font>修改角色</h1><br>
  
    角色:&nbsp;<s:select name="user.role.role_id" id="role_id" list="roleList" listKey="role_id" listValue="role_name" cssClass="select_show" headerKey="" headerValue="请选择角色"></s:select><br>
    
       <input type="button" class="mbtn" value="修改" onclick="toSub();">     
       <input type="hidden" value="${user.user_id}"> 
      
   </form>
   </div>
  </body>
</html>
