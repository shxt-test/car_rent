<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
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
    <title>jQuery实现列表框双向选择操作-Helloweba演示平台</title>
        <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/main.css" />
        <style type="text/css">
        .demo{width:450px; margin:100px auto}
        .select_side{float:left; width:200px}
        select{width:180px; height:200px}
        .select_opt{float:left; width:40px; height:15%; margin-top:80px}
        .select_opt p{width:26px; height:26px; margin-top:6px; background:url(<%=path %>/resource/admin/images/arr.gif) no-repeat; cursor:pointer; text-indent:-999em}
        .select_opt p#toright{background-position:2px 0}
        .select_opt p#toleft{background-position:2px -22px}
        .sub_btn{clear:both; height:42px; line-height:42px; padding-top:10px; text-align:center}
        </style>
        <script type="text/javascript">
            $(function(){
                var leftSel = $("#selectL");
                var rightSel = $("#selectR");
                $("#toright").bind("click",function(){      
                    leftSel.find("option:selected").each(function(){
                        $(this).remove().appendTo(rightSel);
                    });
                });
                $("#toleft").bind("click",function(){       
                    rightSel.find("option:selected").each(function(){
                        $(this).remove().appendTo(leftSel);
                    });
                });
                leftSel.dblclick(function(){
                    $(this).find("option:selected").each(function(){
                        $(this).remove().appendTo(rightSel);
                    });
                });
                rightSel.dblclick(function(){
                    $(this).find("option:selected").each(function(){
                        $(this).remove().appendTo(leftSel);
                    });
                });
            
            });
            
            function toSub(){
            	
            	$("#selectR option").prop("selected" ,true);
            	authForm.submit();
            }
        </script>
</head>

<body>
	   <form action="sys/updateAuthorizeRoleAction.action" method="post" name="authForm">
	    <div id="main">
	      <div class="demo">
	         <div class="select_side">
	         <p><font color="red">${role.role_name}</font>未拥有的菜单</p>
	         <s:select list="unSelectMenuList" id="selectL" multiple="true" listKey="menu_id" listValue="menu_name"></s:select>
	       
	         </div>
	         <div class="select_opt">
	            <p id="toright" title="添加">&gt;</p>
	            <p id="toleft" title="移除">&lt;</p>
	         </div>
	         <div class="select_side">
	             <p>已拥有的菜单</p>
	             <s:select list="selectMenuList" name="menuIds" id="selectR" multiple="true" listKey="menu_id" listValue="menu_name"></s:select>
	         </div>
	         <div class="sub_btn"><input type="button" id="sub" value="更新菜单信息" onclick="toSub()"/></div>
	      </div>
	      <s:hidden name="role.role_id"></s:hidden>
	    </div>
	    </form>
</body>
</html>