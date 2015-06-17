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
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
    <script type="text/javascript">
        function toSub(){
        	var data=$("#menuForm").serialize();
        	$.post("sys/addbrandCarTypeAction.action",data,function(data){
        		if(data.flag=="success"){
        			var dialog = top.dialog.get(window);
		            dialog.close(data.flag);
		            dialog.remove();
        		}else{
        			alert(data.message);
        			$("#menuName").focus();
        		}
            });
        	}
    </script>
</head>

<body>
    <div class="formbody">
        <form  method="post" id="menuForm" >
	    <ul class="forminfo">
		    <li><label>品牌名称</label><input name="carType.type_name" id="menuName" type="text" class="dfinput" /><i>名称不能超过10个字符</i></li>
		    <li><label>图标</label>
		      <cite>
		          <input name="carType.icon" type="radio" value="icon01.png" checked="checked" /><img src="<%=path %>/resource/menu/icon01.png" alt="未找到"/>&nbsp;&nbsp;&nbsp;&nbsp;
		          <input name="carType.icon" type="radio" value="icon02.png" /><img src="<%=path %>/resource/menu/icon02.png" alt="未找到"/>&nbsp;&nbsp;&nbsp;&nbsp;
		          <input name="carType.icon" type="radio" value="icon03.png" /><img src="<%=path %>/resource/menu/icon03.png" alt="未找到"/>&nbsp;&nbsp;&nbsp;&nbsp;
		          <input name="carType.icon" type="radio" value="icon04.png" /><img src="<%=path %>/resource/menu/icon04.png" alt="未找到"/>
		      </cite>
		    
		    </li>
		    <li><label>&nbsp;</label><input type="button" class="btn" value="确认保存" onclick="toSub()"/></li>
	    </ul>
	    </form>
    </div>

</body>

</html>