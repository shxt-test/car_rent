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
    <title> 汽车列表 </title>
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
     <script type="text/javascript">
        function toPage(type){
            var page_now = $("#page_now").val();
            var total_pages = $("#total_pages").val();
            
            switch(type){
              case "1"://首页
                  $("#page_now").val(1);
                  break;
              case "2":
                  if(parseInt(page_now)<=1){
                      alert("没有上一页了");
                      return false;
                  }
                  $("#page_now").val(parseInt(page_now)-1);
                  break;
              case "3":
                  if(parseInt(page_now)>=parseInt(total_pages)){
                      alert("没有下一页了");
                      return false;
                  }
                  
                  $("#page_now").val(parseInt(page_now)+1);
                  
                  break;
                  
              case "4"://尾页
                   $("#page_now").val($("#total_pages").val());
                  break;
              case "5"://查询
                   $("#page_now").val(1);
                  break;
            }
            
            searchForm.submit();
        }
        
        $(function(){
            toLoadBrand();
        });
        //加载所有品牌
        function toLoadBrand(){
            $.post("sys/toLoadBrandCarInfoAction.action",function(data){
                for(var i=0;i<data.length;i++){
                    $("#brand").append("<option  value='"+data[i].type_id+"'>"+data[i].type_name+"</option>");
                    
                }
            })
        };
        
        //加载所有类型
        function toLoadType(){
            
            var bra_id = $("#brand").val();
            $("#type").html("<option value=''>请选择类型</option>");
            
            $.post("sys/toLoadTypeCarInfoAction.action",{bra_id:bra_id},function(data){
                for(var i=0;i<data.length;i++){
                    $("#type").append("<option value='"+data[i].type_id+"'>"+data[i].type_name+"</option>");
                }
            })
        };
        
    </script>
    
    <script type="text/javascript">
        $(function(){
        	$(".tablelist tbody tr ").click(function(){
        		$(this).find(":radio").prop("checked",true);
        	})
        })
        
        /**
         * 关于汽车添加操作
         */
         function toAddDialog(){
        	//成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                width:700,
                height:700,
                title: '添加汽车',
                url:'sys/toAddCarInfoAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
                if (this.returnValue=="success") {
                   // alert(this.returnValue);
                   //自动刷新
                   window.location.reload();
                }

            }
            });
            d.showModal();
         }
        /**
         * 关于汽车信息的更新操作
         * */
         function toUpdateDialog(){
            //判断改用户选择
            var carInfo_id = $("input[name='id']:checked").val();
            if(carInfo_id){
            	//成功需要注意jquery的版本必须是1.7+以上
	            var d = top.dialog({
	                width:700,
	                height:700,
	                title: '更新汽车信息',
	                url:'sys/toUpdateCarInfoAction.action?carInfo_id='+carInfo_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
	                onclose: function () {
	                if (this.returnValue=="success") {
	                   // alert(this.returnValue);
	                   //自动刷新
	                   window.location.reload();
	                }
	
	            }
	            });
	            d.showModal();
            }else{// "" false null undefined 0
            	alert("请选中一条记录进行操作!");
                return;
            }
         }
        
        function toLogOff(){
        	
        	var radio = $("input[name='id']:checked");
        	
        	if(radio.val()){
                var status = $.trim(radio.parent().parent().find("span").text());
                if(status!="报废"){
                    status="<font color='red'>报废</font>"
                }else{
                	status="<font color='green'>可租</font>"
                }
        	var d = top.dialog({
			    title: '提示信息',
			    content: '你是否要对该车进行'+status+'操作',
			    okValue: '确定',
			    ok: function () {
			        this.title('提交中…');
			          var params = {
                            carInfo_id:radio.val()
                        }
			          $.post("sys/toUpdateStatusCarInfoAction.action",params,function(data){
                            if(data.flag=="success"){
                                alert(data.message);

                                if($(status).text()=="可租"){
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
			d.show();
        	}else{// "" false null undefined 0
                alert("请选中一条记录进行操作!");
                return;
            }
        }
        
    </script>

</head>

<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					<a href="#">首页</a>
				</li>
			</ul>
		</div>
        <!-- 工具栏 -->
		<div class="tools" style="padding-top: 5px;">
		     <!-- 查询条件 -->
			<form action="sys/findCarInfoAction.action" method="post"
				name="searchForm">
				<ul class="seachform">
					<li>
						<label>车辆名称</label>
						<s:textfield name="query.carinfo_name" cssClass="scinput" />
					</li>
					<li><label>品牌</label>
		                <select name="query.parent_id" id="brand" onchange="toLoadType()" class="select_show">
		                    <option value="">请选择品牌</option>
		                </select>
		            </li>
		            <li><label>类型</label>
		                <select id="type" name="query.child_id" class="select_show">
		                    <option value="">请选择类型</option>
		                    <s:if test="query.parent_id!=null">
                                
                            </s:if>
		                </select>
		            </li>
					<li>
						<label>&nbsp</label>
						<input type="button" class="scbtn" value="查询"
							onclick="toPage('5')" />
					</li>
				</ul>
				<s:hidden name="pageBean.totalPages" id="total_pages"></s:hidden>
				<s:hidden name="pageBean.pageNow" id="page_now"></s:hidden>
			</form>
			<!-- 其他操作 -->
	        <ul class="seachform1">
		        <li style="cursor: pointer;" onclick="toAddDialog()"><span><img src="<%=path %>/resource/admin/images/t01.png" /></span>添加</li>
		        <li style="cursor: pointer;" onclick="toUpdateDialog()"><span><img src="<%=path %>/resource/admin/images/t02.png" /></span>编辑</li>
		        <li style="cursor: pointer;" onclick="toLogOff()"><span><img src="<%=path %>/resource/admin/images/t05.png" /></span>变更</li>
	        </ul>
		    
       </div>
	    

		<table class="tablelist">
	        <thead>
		        <tr>
			        <th></th>
			        <th>编号</th>
			        <th>车牌号码</th>
			        <th>车辆名称</th>
			        <th>租借金额</th>
			        <th>车辆保证金</th>
			        <th>车辆类型</th>
			        <th>状态</th>
			        <th>详细信息</th>
		        </tr>
	        </thead>
        <tbody>
            <s:iterator value="pageBean.datas" var="carInfo" status="st">
                <tr>
		            <td><input name="id" type="radio" value="<s:property value="#carInfo.car_id"/>" /></td>
		            <td><s:property value="#st.count"/></td>
		            <td><s:property value="#carInfo.car_code"/></td>
		            <td><s:property value="#carInfo.car_name"/></td>
		            <td><s:property value="#carInfo.rent_price"/></td>
		            <td><s:property value="#carInfo.deposit"/></td>
		            <td><s:property value="#carInfo.carType.type_name"/></td>
		            
		            <td>
	                    <s:if test="#carInfo.car_status==1">
	                       <span style="color:green;font-weight:blod;">可租</span>
	                    </s:if>
	                    <s:elseif test="#carInfo.car_status==2">
                            <span style="color:blue;font-weight:blod;">预定</span>
	                    </s:elseif>
	                    <s:elseif test="#carInfo.car_status==3">
                            <span style="color:red;font-weight:blod;">报废</span>
	                    </s:elseif>
	                    <s:else>
                            <span style="color:#CC9999;font-weight:blod;">租赁</span>
	                    </s:else>
                    </td>
		            
		            <td><a href="javascript:void(0)" onclick="getCarInfo()">查看详情</a></td>
	            </tr> 
            </s:iterator>
        </tbody>
    </table>
	     <div class="pagin">
	        <div class="message">共<i class="blue">${pageBean.totalCount}</i>条记录，当前显示第&nbsp;<i class="blue">${pageBean.pageNow}&nbsp;/${pageBean.totalPages}</i>页</div>
	        <ul class="paginList">
	        <li class="paginItem"><a href="javascript:void(0);" onclick="toPage('1')">首页</a></li>
	        <li class="paginItem"><a href="javascript:void(0);" onclick="toPage('2')">上一页</a></li>
	        <li class="paginItem"><a href="javascript:void(0);" onclick="toPage('3')">下一页</a></li>
	        <li class="paginItem"><a href="javascript:void(0);" onclick="toPage('4')">尾页</a></li>
	        </ul>
	    </div>
     </div>
     

    
    <script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
    </script>
    </body>
    </html>
