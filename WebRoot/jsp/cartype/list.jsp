
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
            }else{
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
        
      //修改类型 
       function toUpdateTypeDialog(){
            if(!$("input[name='id']:checked").val()){
                alert("请选中一条记录进行操作")
                return false;
            }else{
                var CarType_id = $("input[name='id']:checked").val();
                alert(CarType_id);
             //成功需要注意jquery的版本必须是1.7+以上
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
        }
    //删除品牌
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
                            +'<a href="javascript:void(0)" onclick="deleteChildDialog(\''+CarType_id+'\')"><font color="blue" >删除</font></a>&nbsp;&nbsp;|&nbsp;&nbsp;'
                            +'<a href="javascript:void(0)" onclick="toLookTypeDialog(\''+CarType_id+'\')"><font color="blue" >查看</font></a>'
				   var show_dialog = dialog({
					 id:"show_dialog",
				    align: 'top',
				    content: content,
				     quickClose: true// 点击空白处快速关闭
				});
				show_dialog.show(obj);
				
        }
        
        
        function deleteParentDialog(){
        	var CarType_id =$("input[name='id']:checked").val();
        	  if(CarType_id){
        		  if(window.confirm("你确定要删除该记录吗？")){
        			  $.get("sys/deleteParentCarTypeAction.action",{type_id:CarType_id},function(data){
        				  if(data.flag=="success"){
        					 var radio = $("input[name='id']:checked");
        					 radio.parent().parent().hide("slow",function(){
        						 $(this).remove();
        					 })
        				 } else{
        					 alert(data.message);
        					 return false;
        				 }
        			  });
        			  
        		  }
            }else{
            	alert("请选择一条记录进行删除");
            	return false;
            }
        }
        
        function deleteChildDialog(CarType_id){
                  if(window.confirm("你确定要删除该记录吗？")){
                      $.get("sys/deleteChildCarTypeAction.action",{type_id:CarType_id},function(data){
                         if(data.flag=="success"){
                        var id =  "#child_"+CarType_id;
                          $(id).slideUp("slow",function(){
                        	  $(this).remove();
                          })
                            dialog.get("show_dialog").remove();
                         } else{
                             alert(data.message);
                             return false;
                         }
                      });
                  }else{
                	  dialog.get("show_dialog").remove();
                  }
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
        <li class="click" onclick="deleteParentDialog()"><span><img src="<%=path %>/resource/admin/images/t03.png" /></span>删除</li>
        <li><span><img src="<%=path %>/resource/admin/images/t04.png" /></span>统计</li>
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
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${carTypeList}" var="parent" varStatus="vs">
        <tr>
        <td><input name="id"  type="radio" value="${parent.type_id}" /></td>
        <td>${vs.count}</td>
        <td>${parent.type_name}</td>
        <td><img alt="" src="<%=path %>/resource/CarType/${parent.icon}"></td>
        <td>
            <c:forEach items="${parent.carTypeList}" var="child" varStatus="vs">
                <a href="javascript:void(0)" id="child_${child.type_id}" onclick="toChildShowWin(this,${child.type_id})">${vs.count}.&nbsp;${child.type_name}</a>&nbsp;&nbsp;
            </c:forEach>
        
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

