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
                url:'shxt/add.html',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
                if (this.returnValue) {
                    alert(this.returnValue);
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

        <li class="selected">
            <span><img src="<%=path%>/resource/admin/images/img01.png"/></span>

            <h2><a href="#">软件界面设计下载</a></h2>

            <p><a href="#">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="toChangeStatus()">变更</a></p>

            <p><a href="#">注销</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">授权</a></p>
        </li>

    </ul>


</div>


</body>

</html>
