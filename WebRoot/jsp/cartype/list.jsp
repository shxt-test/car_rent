
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="s" uri="/struts-tags" %>
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
    <link rel="stylesheet" href="<%=path %>/resource/admin/artDialog/css/ui-dialog.css">
    <script src="<%=path %>/resource/admin/artDialog/dist/dialog-plus.js"></script>
    <script type="text/javascript">
      //选择一行
        $(function(){
            $(".tablelist tbody tr ").click(function(){
                $(this).find(":radio").prop("checked",true);
            })
        })
   
      //添加品牌
        function toAddDialog(){
             //成功需要注意jquery的版本必须是1.7+以上
                    var d = top.dialog({
                        id:"rightFrame",
                        width:700,
                        height:200,
                        title: '添加品牌',
                        url:'sys/toAddCarTypeAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                        onclose: function () {
                        if (this.returnValue=="success") {
                           // alert(this.returnValue);
                           window.location.reload();
                        }
                    }
                    });
                    d.showModal();
        }
      
      //添加类型
       function toAddChildDialog(){
             //成功需要注意jquery的版本必须是1.7+以上
                    var d = top.dialog({
                        id:"rightFrame",
                        width:700,
                        height:350,
                        title: '添加型号',
                        url:'sys/toAddChildCarTypeAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                        onclose: function () {
                        if (this.returnValue=="success") {
                           // alert(this.returnValue);
                           window.location.reload();
                        }
                    }
                    });
                    d.showModal();
        }
      
      //修改品牌
        function toUpdateDialog(){
            
            if(!$("input[name='id']:checked").val()){
                alert("请选中一条记录进行操作")
                return false;
            }
              var radio = $("input[name='id']:checked");
               if(radio.val()){
                var status = $.trim(radio.parent().parent().find("span").text());
                if(status=="禁用"){
                    alert("该品牌禁用，不能进行修改操作");
                    return false;
                }
                else{
                var CarType_id = $("input[name='id']:checked").val();
             //成功需要注意jquery的版本必须是1.7+以上
                    var d = top.dialog({
                        id:"rightFrame",
                        width:700,
                        height:350,
                        title: '修改品牌名称',
                        url:'sys/toUpdateBrandCarTypeAction.action?type_id='+CarType_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
                        onclose: function () {
                        if (this.returnValue=="success") {
                           // alert(this.returnValue);
                           window.location.reload();
                        }
                    }
                    });
                    d.showModal();
            }
            }
         
        }
        
      //修改品牌类型 
       function toUpdateTypeDialog(CarType_id){
             //成功需要注意jquery的版本必须是1.7+以上
               var radio = $("input[name='id']:checked");
               if(radio.val()){
                var status = $.trim(radio.parent().parent().find("span").text());
                if(status=="禁用"){
                    alert("该品牌禁用，不能进行修改操作");
                    return false;
                }else{
                     var d = top.dialog({
                        id:"rightFrame",
                        width:700,
                        height:350,
                        title: '修改类型名称',
                        url:'sys/toUpdateBrandCarTypeAction.action?type_id='+CarType_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
                        onclose: function () {
                        if (this.returnValue=="success") {
                           // alert(this.returnValue);
                           window.location.reload();
                        }
                    }
                    });
                    d.showModal();
                }
        }}
      
    //删除品牌没有用
       function toDelete(){
            
            if(!$("input[name='id']:checked").val()){
                alert("请选中一条删除记录")
                return false;
            }else{
                 var CarType_id = $("input[name='id']:checked").val();
                 
                 var result = confirm("确认要删除吗？");
                  if(result==true){
                     var href=window.location.href="CarTypeDeleteServlet.do?CarType.CarType_id="+CarType_id;
                 }
            }
                  
         }
       
        function toChildShowWin(obj,CarType_id){
            var content = '<a href="javascript:void(0)" onclick="toUpdateTypeDialog(\''+CarType_id+'\')"><font color="blue" >编辑</font></a>&nbsp;&nbsp;|&nbsp;&nbsp;'
                            +'<a href="javascript:void(0)" onclick="deleteChildDialog(\''+CarType_id+'\')"><font color="blue" >变更</font></a>&nbsp;&nbsp;|&nbsp;&nbsp;'
                            +'<a href="javascript:void(0)" onclick="toLookTypeDialog(\''+CarType_id+'\')"><font color="blue" >查看</font></a>'
                   var show_dialog = dialog({
                     id:"show_dialog",
                    align: 'top',
                    content: content,
                     quickClose: true// 点击空白处快速关闭
                });
                show_dialog.show(obj);
                
        }
        
        //变更品牌操作
        function deleteParentDialog(){
             //判断改用户选择
            //  可以用struct标签获取id<s:property value="#session.session_user.user_id"/>
            var radio = $("input[name='id']:checked");
               if(radio.val()){
                var status = $.trim(radio.parent().parent().find("span").text());
                if(status=="可用"){
                    status="<font color='red'>禁用</font>"
                }else{
                    status="<font color='red'>可用</font>"
                    
                }
                var d = top.dialog({
                    title: '提示信息',
                    content: '你是否要对改品牌进行'+status+'操作吗?',
                    okValue: '确定',
                    ok: function () {
                        this.title('提交中…');
                        var params = {
                            type_id:radio.val()
                        }
                        $.post("sys/toUpdateStatusCarTypeAction.action",params,function(data){
                            if(data.flag=="success"){
                                if($(status).text()=="可用"){
                                  radio.parent().parent().find("span").css("color","green");
                                  radio.parent().parent().find("span").html($(status).text());
                                }else{
                                  radio.parent().parent().find("span").css("color","red");
                                  radio.parent().parent().find("span").html($(status).text());
                                }
                                return;
                            }else{
                                alert(data.message);
                                return;
                            }
                        });
                       
                    },
                    cancelValue: '取消',
                    cancel: function () {}
                });
                d.showModal();
            }else{
                alert("请选中一条记录进行操作!");
                return;
            }
        }
        
        //变更类型为启用
        function deleteChildDialog(CarType_id){
                var radio = $("input[name='id']:checked");
               if(radio.val()){
                var status = $.trim(radio.parent().parent().find("span").text());
                if(status=="禁用"){
                    alert("该品牌禁用，不能进行修改操作");
                    return false;
                }else{
                     if(window.confirm("你确定要对该品牌类型进行变更操做吗？")){
                         $.post("sys/toUpdateStatusCarTypeAction.action",{type_id:CarType_id},function(data){
                            if(data.flag=="success"){
                               window.location.reload();
                                return;
                            }else{
                                alert(data.message);
                                return;
                            }
                        });
                  }else{
                      dialog.get("show_dialog").remove();
                  }
                } 
                }
                 
        }
        
        //统计信息
       function tjDialog(){
             //成功需要注意jquery的版本必须是1.7+以上
                    var d = top.dialog({
                        id:"rightFrame",
                        width:700,
                        height:350,
                        title: '统计信息',
                        url:'sys/tjTestCarTypeAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                        onclose: function () {
                        if (this.returnValue=="success") {
                           // alert(this.returnValue);
                           window.location.reload();
                        }
                    }
                    });
                    d.showModal();
        }
</script>

</head>


<body>

    <div class="place">
    <span>位置：</span>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
        <ul class="toolbar">
        <li class="click" onclick="toAddDialog()"><span><img src="<%=path %>/resource/admin/images/t01.png" /></span>添加品牌</li>
        <li class="click" onclick="toUpdateDialog()"><span><img src="<%=path %>/resource/admin/images/t02.png" /></span>修改</li>
        <li class="click" onclick="deleteParentDialog()"><span><img src="<%=path %>/resource/admin/images/t03.png" /></span>变更</li>
        <li class="click" onclick="tjDialog()"><span><img src="<%=path %>/resource/admin/images/t04.png" /></span>统计</li>
        </ul>
        <ul class="toolbar1">
       <a href="javascript:void(0)"> <li   onclick="toAddChildDialog()"><span><img src="<%=path %>/resource/admin/images/t01.png" /></span>添加类型</li></a>
        </ul>
    
    </div>
    
    
    <table class="tablelist">
        <thead>
        <tr>
        <th><input name="" type="checkbox" value="" /></th>
        <th width="50">编号</th>
        <th>汽车名称</th>
        <th>图标</th>
        <th>子菜单</th>
        <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${carTypeList}" var="parent" varStatus="vs">
        <tr>
        <td><input name="id"  type="radio" value="${parent.type_id}" /></td>
        <td>${vs.count}</td>
        <td>${parent.type_name}</td>
        <td><img alt="" src="<%=path %>/upload/cartype/${parent.icon}"></td>
        <td>
            <c:forEach items="${parent.carTypeList}" var="child" varStatus="vs">
                    <c:if test="${child.type_status==1}">
                     <a href="javascript:void(0)" id="child_${child.type_id}" onclick="toChildShowWin(this,${child.type_id})">${vs.count}.&nbsp;<font color="red">${child.type_name}</font></a>&nbsp;&nbsp;
                    </c:if>
                    <c:if test="${child.type_status==2}">
                         <a href="javascript:void(0)" id="child_${child.type_id}" onclick="toChildShowWin(this,${child.type_id})">${vs.count}.&nbsp;${child.type_name}</a>&nbsp;&nbsp;
                    </c:if>
            </c:forEach>
        </td>
        <td >
            <c:if test="${parent.type_status==1}">
                  <span style="color:green;font-weight:blod;">可用</span>
           </c:if>
            <c:if test="${parent.type_status==2}" >
                 <span style="color:red;font-weight:blod;">禁用</span>
            </c:if>
        </td>
        </tr> 
       </c:forEach>
        </tbody>
    </table>
    </div>
    </div>
    
    <script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
    </script>

</body>

</html>

