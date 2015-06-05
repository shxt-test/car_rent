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
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
    
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
    <script type="text/javascript" src="<%=path %>/common/upload_img.js"></script>
    <script type="text/javascript">
        function toSub(){
        	var role_name=$.trim($("#role_name").val());
        	var old_role_name=$.trim($("#old_role_name").val());
        	if(role_name.length==0||role_name.length>20){
        		alert("你输入的角色名称不合法，请重新输入");
        		return false;
        	}
        	
        	var photo = $.trim($("#photo").val());
        	if(photo.length>0){
        		var flag = uploadValid(photo);
        		if(!flag){
        			alert("图片格式不正确");
        			$("#photo").val("");
        			 return false;
        		}
        	}
        	if(old_role_name!=role_name){
        	$.post("sys/toCheckRoleAction.action",{role_name:role_name},function(data){
        		
        		if(data.flag=="success"){
        			roleForm.submit();
        		}else{
        			$("#error").css("color","red");
        			$("#error").html(data.message);
        			$("#error").focus();
        			return;
        		}
        	});
        	}else{
        		roleForm.submit();
        	}
        }

    </script>
</head>

<body>



<div class="formbody">

    <form action="sys/updateRoleAction.action" method="post" name="roleForm" enctype="multipart/form-data">

    <ul class="forminfo">
        <li><label>角色名称</label><input name="role.role_name" id="role_name" type="text" class="dfinput" value="${role.role_name}"/><i id="error" >标题不能超过20个字符</i></li>
        <li><label>头像</label><input name="photo" type="file" id="photo" class="dfinput"/><i>多个关键字用,隔开</i></li>
        <li><label>文章内容</label><textarea name="role.role_desc" id="role_desc" cols="" rows="" class="textinput">${role.role_desc}</textarea></li>
        <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存" onclick="toSub()"/></li>
         <li><label>&nbsp;</label><img alt="" src="<%=path %>/upload/role/${role.photo}"></li>
    </ul>
    <input type="hidden" name="role.role_id" value="${role.role_id}">
    <input type="hidden" id="old_role_name" value="${role.role_name}">
</form>

</div>


</body>

</html>
