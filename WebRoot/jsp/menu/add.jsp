<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
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
        	alert($("#menuForm").serialize())
        	$.post("sys/addMenuAction.action",$("#menuForm").serialize(),function(data){
        		
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
        <li><label>图片</label>
          <cite>
        <input type="radio" name="menu.icon" value="icon01.png" checked="checked"/>&nbsp;&nbsp;<img alt="图片未加载" src="<%=path %>/resource/menu/icon01.png">&nbsp;&nbsp;
        <input  type="radio" name="menu.icon" value="icon02.png" />&nbsp;&nbsp;<img alt="图片未加载" src="<%=path %>/resource/menu/icon02.png">&nbsp;&nbsp;
        <input  type="radio" name="menu.icon" value="icon03.png" />&nbsp;&nbsp;<img alt="图片未加载" src="<%=path %>/resource/menu/icon03.png">&nbsp;&nbsp;
        <input  type="radio" name="menu.icon" value="icon04.png" />&nbsp;&nbsp;<img alt="图片未加载" src="<%=path %>/resource/menu/icon04.png">
        </cite>
        <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存" onclick="toSub();"/></li>
    </ul>
    </form>

</div>


</body>

</html>