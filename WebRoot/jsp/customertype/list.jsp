<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
    <title>客户类型管理</title>
     <link href="<%=path%>/resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <!-- 引入JQuery支持的库 -->
    <script type="text/javascript" src="<%=path%>/resource/admin/js/jquery.js"></script>
    <!-- 引入artDailog支持的库 -->
    <link rel="stylesheet" href="<%=path%>/resource/admin/artDialog/css/ui-dialog.css">
    <script type="text/javascript" src="<%=path%>/resource/admin/artDialog/dist/dialog-plus.js"></script>

    <script language="javascript">
        $(function () {
            //导航切换
            $(".imglist li").click(function () {
                $(".imglist li.selected").removeClass("selected")
                $(this).addClass("selected");
            })
        })
    </script>

    <!-- 关于功能测试代码 -->
    <script type="text/javascript">
        function toAddDialog(){
            //测试artDialog是否成功
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                id:"rightFrame",
                width:700,
                height:500,
                title: '欢迎',
                url:'sys/toAddCustomerTypeAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
                if (this.returnValue=="success") {
                    window.location.reload();
                }

            }
            });
            d.showModal();
        }
         function toUpdateCustomerTypeDialog(c_type_id){
            //测试artDialog是否成功
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                id:"rightFrame",
                width:700,
                height:500,
                title: '欢迎',
                url:'sys/toUpdateCustomerTypeAction.action?c_type_id='+c_type_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
                if (this.returnValue=="success") {
                    window.location.reload();
                }

            }
            });
            d.showModal();
        }

        //变更状态方法
        function toChangeStatus(){
            var d = dialog({
                title: '提示',
                content: '按钮回调函数返回 false 则不许关闭',
                okValue: '确定',
                ok: function () {
                    this.title('提交中…');
                    return false;
                },
                cancelValue: '取消',
                cancel: function () {}
            });
            d.show();
        }

        function deleteDialog(c_type_id){
        	 var d = top.dialog({
                id:"rightFrame",
                width:700,
                height:500,
                title: '欢迎',
                url:'sys/deleteCustomerTypeAction.action?c_type_id='+c_type_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
                if (this.returnValue=="success") {
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
    <ul class="placeul">
        <li>客户类型管理</li>
    </ul>
</div>

<div class="rightinfo">

    <div class="tools">
        <ul class="toolbar">

            <li class="click" onclick="toAddDialog()"><span><img src="<%=path%>/resource/admin/images/t01.png"/></span>新建客户类型</li>
        </ul>

    </div>


    <ul class="imglist">

         <c:forEach  items="${typeList}"  var="type"  >
          <li class="selected" title="">
            <span><img alt="图片未加载" src="<%=path%>/upload/customertype/<c:out value="${type.photo}" default="default.png"></c:out> "/></span>
           
            <h2><c:out value="${type.c_type_name}"></c:out></h2>
              
            <p><a href="javascript:void(0)" onclick="toUpdateCustomerTypeDialog('${type.c_type_id}')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
               <a href="javascript:void(0)" onclick="deleteDialog('${type.c_type_id}')">删除</a></p>
            <p><a href="javascript:void(0)" onclick="toDelete(this,'${type.c_type_id}')">注销</a>&nbsp;&nbsp;&nbsp;&nbsp;
               <a href="javascript:void(0)" onclick="toAuthorize('${type.c_type_id}')">授权</a></p>
        </c:forEach>
          

        </li>

    </ul>


</div>


</body>

</html>
