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
    <link href="<%=path%>/resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <!-- 引入JQuery支持的库 -->
    <script type="text/javascript" src="<%=path%>/resource/admin/js/jquery.js"></script>
    <!-- 引入artDailog支持的库 -->
    <link rel="stylesheet" href="<%=path%>/resource/admin/artDialog/css/ui-dialog.css">
    <script type="text/javascript" src="<%=path%>/resource/admin/artDialog/dist/dialog-plus.js"></script>
    <script type="text/javascript">
        function toSub(){
            //经过一切处理之后，在Action或者Servlet中跳转到Message.jsp显示提示信息内容
            //模仿操作！
            window.location.href="message.html";
        }

    </script>
</head>

<body>
    <div class="formbody">

    <ul class="forminfo">
        <li><label>类型名称</label><input name="customerType.c_type_name" type="text" class="dfinput"/><i>名称不能超过30个字符</i></li>
        <li><label>打折率</label><input name="customerType.c_discount" type="text" class="dfinput"/><i>多个关键字用,隔开</i></li>
        
        <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存" onclick="toSub();"/></li>
    </ul>


</div>


</body>

</html>