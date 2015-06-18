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
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
    <script language="javascript" type="text/javascript" src="<%=path %>/resource/admin/My97DatePicker/WdatePicker.js"></script>

</head>

<body>

    <div class="mformbody" >
        <form action="sys/reserveCarInfoAction.action" name="carForm" method="post" enctype="multipart/form-data">
        <ul class="mforminfo">
            <li><label>车牌号</label><input name="carInfo.car_code" id="car_code" type="text" class="mdfinput" readonly="readonly"  value="${carInfo.car_code}"/></li>
            <li><label>车辆名称</label><input name="carInfo.car_name" id="car_name" type="text" class="mdfinput" readonly="readonly" value="${carInfo.car_name}" /></li>
            <li><label>预定人姓名</label><input name="carInfo.reserve_user_name" id="reserve_user_name" type="text" class="mdfinput" value="" /></li>
            <li><label>预定人联系方式</label><input name="carInfo.reserve_tel" id="reserve_tel" type="text" class="mdfinput" /></li>
            <li><label>预定日期</label><input name="carInfo.reserve_date" id="reserve_date" type="text" class="mdfinput" onClick="WdatePicker()"/></li>
            <li><label>&nbsp;</label><input  type="submit" class="mbtn" value="确认预定"/></li>
        </ul>
            <input name="carInfo.car_id" type="hidden" value="${carInfo.car_id}"/>
        </form>
    </div>
</body>

</html>