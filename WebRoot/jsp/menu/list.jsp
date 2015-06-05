
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
        function toAddDialog(){
    
             //成功需要注意jquery的版本必须是1.7+以上
                    var d = top.dialog({
                        id:"rightFrame",
                        width:700,
                        height:200,
                        title: '添加父菜单',
                        url:'sys/toAddMenuAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                        onclose: function () {
                        if (this.returnValue=="success") {
                           // alert(this.returnValue);
                           window.location.reload();
                        }
                    }
                    });
                    d.showModal();
        }
      
       function toAddChildDialog(){
             //成功需要注意jquery的版本必须是1.7+以上
                    var d = top.dialog({
                        id:"rightFrame",
                        width:700,
                        height:350,
                        title: '添加子菜单',
                        url:'sys/toAddChildMenuAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                        onclose: function () {
                        if (this.returnValue=="success") {
                           // alert(this.returnValue);
                           window.location.reload();
                        }
                    }
                    });
                    d.showModal();
        }
      
        function toUpdateDialog(){
            
            if(!$("input[name='id']:checked").val()){
                alert("请选中一条记录进行操作")
                return false;
            }else{
                var menu_id = $("input[name='id']:checked").val();
             //成功需要注意jquery的版本必须是1.7+以上
                    var d = top.dialog({
                        id:"rightFrame",
                        width:700,
                        height:350,
                        title: '修改父菜单',
                        url:'MenuParentUpdateServlet.do?menu.menu_id='+menu_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
        
       
    
       function toDelete(){
            
            if(!$("input[name='id']:checked").val()){
                alert("请选中一条删除记录")
                return false;
            }else{
                 var menu_id = $("input[name='id']:checked").val();
                 
                 var result = confirm("确认要删除吗？");
                  if(result==true){
                     var href=window.location.href="MenuDeleteServlet.do?menu.menu_id="+menu_id;
                 }
            }
                  
         }
       
        function toChildShowWin(obj,menu_id){
        
        	var content = '<a href="javascript:void(0)" onclick="toUpdateDialog(\''+menu_id+'\')"><font color="blue" >编辑</font></a>&nbsp;&nbsp;|&nbsp;&nbsp;'
                            +'<a href="javascript:void(0)" onclick="deleteChildDialog(\''+menu_id+'\')"><font color="blue" >删除</font></a>&nbsp;&nbsp;|&nbsp;&nbsp;'
                            +'<a href="javascript:void(0)" onclick="toLookDialog(\''+menu_id+'\')"><font color="blue" >查看</font></a>'
				   var show_dialog = dialog({
					 id:"show_dialog",
				    align: 'top',
				    content: content,
				     quickClose: true// 点击空白处快速关闭
				});
				show_dialog.show(obj);
				
        }
        
        
        function deleteParentDialog(){
        	var menu_id =$("input[name='id']:checked").val();
        	  if(menu_id){
        		  if(window.confirm("你确定要删除该记录吗？")){
        			  $.get("sys/deleteParentMenuAction.action",{menu_id:menu_id},function(data){
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
        
        function deleteChildDialog(menu_id){
                  if(window.confirm("你确定要删除该记录吗？")){
                      $.get("sys/deleteChildMenuAction.action",{menu_id:menu_id},function(data){
                         if(data.flag=="success"){
                        var id =  "#child_"+menu_id;
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
        <li class="click" onclick="toAddDialog()"><span><img src="<%=path %>/resource/admin/images/t01.png" /></span>添加父节点</li>
        <li class="click" onclick="toUpdateDialog()"><span><img src="<%=path %>/resource/admin/images/t02.png" /></span>修改</li>
        <li class="click" onclick="deleteParentDialog()"><span><img src="<%=path %>/resource/admin/images/t03.png" /></span>删除</li>
        <li><span><img src="<%=path %>/resource/admin/images/t04.png" /></span>统计</li>
        </ul>
        <ul class="toolbar1">
       <a href="javascript:void(0)"> <li   onclick="toAddChildDialog()"><span><img src="<%=path %>/resource/admin/images/t01.png" /></span>添加子菜单</li></a>
        </ul>
    
    </div>
    
    
    <table class="tablelist">
        <thead>
        <tr>
        <th><input name="" type="checkbox" value="" /></th>
        <th width="50">编号<i class="sort"><img src="<%=path %>/resource/admin/images/px.gif" /></i></th>
        <th>菜单名称</th>
        <th>图标</th>
        <th>子菜单</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${menuList}" var="parent" varStatus="vs">
        <tr>
        <td><input name="id"  type="radio" value="${parent.menu_id}" /></td>
        <td>${vs.count}</td>
        <td>${parent.menu_name}</td>
        <td><img alt="" src="<%=path %>/resource/menu/${parent.icon}"></td>
        <td>
            <c:forEach items="${parent.menuList}" var="child" varStatus="vs">
                <a href="javascript:void(0)" id="child_${child.menu_id}" onclick="toChildShowWin(this,${child.menu_id})">${vs.count}.&nbsp;${child.menu_name}</a>&nbsp;&nbsp;
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

