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
    <title>新建角色</title>
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
      <!-- 引入JQuery支持的库 -->
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
    <script type="text/javascript" src="<%=path %>/resource/common/upload_img.js"></script>
    <script type="text/javascript">
        function toSub(){
            //经过一切处理之后，在Action或者Servlet中跳转到Message.jsp显示提示信息内容
            //模仿操作！
            //window.location.href="message.html";
            
             var type_name=$.trim($("#type_name").val());
             var old_type_name=$.trim($("#old_type_name").val());
            if(type_name.length==0||type_name.length>20){
                alert("您输入的格式不合法");
            }
            var photo=$("#photo").val();
            if(photo.length>0){
                var flag=uploadValid(photo);
                alert(flag);
                if(!flag){
                    alert("请上传正确的图片");
                    $("#photo").val();
                    return false;
                }
            }
            if(type_name!=old_type_name){
            	   $.post("sys/toCheckCarTypeAction.action",{type_name:type_name},function(data){
                if(data.flag=="error"){
                    $("#error").css("color","red");
                    $("#error").html(data.message);
                    $("#error").focus();
                    return false;
                }
                else{
                   roleForm.submit();
                }
                }
            );
            }
            else{
            	   roleForm.submit();
            }
        }
    </script>
</head>

<body>
	   <div class="formbody">
       <form action="sys/toUpdateCarTypeAction.action" method="post" name="roleForm" enctype="multipart/form-data">
        <ul class="forminfo">
            <li><label>品牌名称</label><input name="carType.type_name" id="type_name" type="text" class="dfinput" value="${carType.type_name}"/><i id="error">标题不能超过30个字符</i></li>
            <li><label>上传头像</label><input name="photo" id="photo" type="file" class="dfinput"/><i>168*126像素</i></li>
            <li><label>显示头像</label><img alt="图像"  src="<%=path %>/upload/cartype/${carType.icon}"/></li>
            <li><label>&nbsp;</label><input type="button" class="btn" value="确认更新" onclick="toSub();"/></li>
        </ul>
           <%-- 隐藏域传递ID --%>
           <input type="hidden" name="carType.type_id"  value="${carType.type_id}">
           <input type="hidden" id="old_type_name" value="${carType.type_name}">
        </form>
    </div>
</body>

</html>