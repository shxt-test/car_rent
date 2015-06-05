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
   <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
       <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function toSub(){
            $.post("sys/addChildMenuAction.action",$("#menuForm").serialize(),function(data){
                
                if(data.flag=="success"){
                         var dialog = top.dialog.get(window);
                          dialog.close(data.flag);
                          dialog.remove();
                }else{
                    alert(data.message);
                    $("#menu_name").focus();
                    return false;
                }
                
            });
           
        }

    </script>
</head>

<body>


<div class="formbody">

    <form method="post" id="menuForm">
    <ul class="forminfo">
        <li><label>菜单名称</label><input name="menu.menu_name" id="menu_name" type="text" class="dfinput"/><i>标题不能超过20个字符</i></li>
        <li><label>父菜单名称</label>
        <s:select list="menuList" name="menu.parent_id" id="menu_id" listKey="menu_id" listValue="menu_name" 
        cssClass="select_show" headerKey="" headerValue="请选择父菜单">
        </s:select>
        <li><label>子菜单路径</label>
            <input type="text" name="menu.url" id="url"  class="dfinput">
        </li>
        <li><label>&nbsp;</label><input  type="button" class="btn" value="确认保存" onclick="toSub();"/></li>
    </ul>
    </form>

</div>


</body>

</html>