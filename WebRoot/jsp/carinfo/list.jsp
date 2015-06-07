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
    <title> 车辆信息管理界面  </title>
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
                title: '添加汽车信息',
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
         * 关于车辆的更新操作
         * */
         function toUpdateDialog(){
            //判断改用户选择
            var user_id = $("input[name='id']:checked").val();
            if(user_id){
                //成功需要注意jquery的版本必须是1.7+以上
                var d = top.dialog({
                    width:700,
                    height:700,
                    title: '新建用户',
                    url:'sys/toUpdateCarInfoAction.action?user_id='+user_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
            //<!--判断是不是修改的自己的-->
            //<s:property value="#session.session_user.user_id"/>
            if(radio.val()==${session_user.user_id}){
                alert('不能对自己进行修改');
                return false;
            }
            if(radio.val()){
                var status = $.trim(radio.parent().parent().find("span").text());
                if(status=="可用"){
                    status="<font color='red'>禁用</font>"
                }else{
                    status="<font color='red'>可用</font>"
                }
            var d = top.dialog({
                title: '提示信息',
                content: '你是否要对该账号进行'+status+'操作码',
                okValue: '确定',
                ok: function () {
                    this.title('提交中…');
                      var params = {
                            user_id:radio.val()
                        }
                      $.post("sys/toUpdateStatusUserAction.action",params,function(data){
                            if(data.flag=="success"){
                                alert(data.message);

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
            d.show();
            }else{// "" false null undefined 0
                alert("请选中一条记录进行操作!");
                return;
            }
        }
        
        function toAllocationRole(){
              //判断该用户选择
            var user_id = $("input[name='id']:checked").val();
            if(user_id){
                //成功需要注意jquery的版本必须是1.7+以上
                var d = top.dialog({
                    width:300,
                    height:200,
                    title: '角色分配',
                    url:'sys/toAllocationRoleUserAction.action?user_id='+user_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
            <form action="sys/findCarInfoAction.action" method="post" name="searchForm">
                <ul class="seachform">
                    <li>
                        <label>车辆名称</label>
                        <s:textfield name="query.car_name" cssClass="scinput" />
                    </li>
                    <li>
                        <label>车辆类型</label>
                    </li>

                    <li>
                        <label>&nbsp</label>
                        <input type="button" class="scbtn" value="查询"
                            onclick="toPage('5')" />
                    </li>
                </ul>
                <s:hidden name="pageBean.totalPages" id="total_pages"></s:hidden>
                <s:hidden name="pageBean.pageNow" id="page_now"></s:hidden>
                <s:hidden name="session_user.user_id" id="sessionUser_id"></s:hidden>
            </form>
            <!-- 其他操作 -->
            <ul class="seachform1">
                <li style="cursor: pointer;" onclick="toAddDialog()"><span><img src="<%=path %>/resource/admin/images/t01.png" /></span>新建</li>
                <li style="cursor: pointer;" onclick="toUpdateDialog()"><span><img src="<%=path %>/resource/admin/images/t02.png" /></span>编辑</li>
                <li style="cursor: pointer;" onclick="toLogOff()"><span><img src="<%=path %>/resource/admin/images/t03.png" /></span>变更</li>
                <li style="cursor: pointer;" onclick="toAllocationRole()"><span><img src="<%=path %>/resource/admin/images/t05.png" /></span>分配角色</li>
            </ul>
            
       </div>
        

        <table class="tablelist">
            <thead>
                <tr>
                    <th></th>
                    <th>编号</th>
                    <th>车牌号</th>
                    <th>车辆名称</th>
                    <th>租借金额</th>
                    <th>车辆颜色</th>
                    <th>购买价格</th>
                    <th>车辆保证金</th>
                    <th>汽车行驶公里数</th>
                    <th>登记人的姓名</th>
                    <th>车辆类型</th>
                    <th>车辆图片</th>
                    <th>车辆状态</th>
                </tr>
            </thead>
        <tbody>
            <s:iterator value="pageBean.datas" var="car" status="st">
                <tr>
                    <td><input name="id" type="radio" value="<s:property value="#car.car_id"/>" /></td>
                    <td><s:property value="#st.count"/></td>
                    <td><s:property value="#car.car_code"/></td>
                    <td><s:property value="#car.car_name"/></td>
                    <td><s:property value="#car.rent_price"/></td>
                    <td><s:property value="#car.car_color"/></td>
                    <td><s:property value="#car.buy_price"/></td>
                    <td><s:property value="#car.deposit"/></td>
                    <td><s:property value="#car.km"/></td>
                    <td><s:property value="#car.create_user_name"/></td>
                    <td><s:property value="#car.carType.type_name" escape="false" default="<font color='red'>没有分配角色</font>"/></td>
                    <td><s:property value="#car.photo"/></td>
                    <td>
                          <s:if test="#car.car_status==1">
                              <span style="color:green;font-weight:blod;">可用</span>
                          </s:if>
                          <s:else>
                               <span style="color:red;font-weight:blod;">禁用</span>
                          </s:else>
                    </td>
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