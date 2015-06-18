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

    <script type="text/javascript">

        /**
         * 图片预览功能--没有封装
         *
         * @return {Boolean}
         */
        function setImagePreview() {

            var docObj = document.getElementById("photo");

            if (!docObj.value.match(/.jpg|.gif|.png|.bmp/i)) {
                alert('图片格式无效！');
                document.getElementById("photo").value = "";
                return false;
            }

            var imgObjPreview = document.getElementById("preview");
            if (docObj.files && docObj.files[0]) {
                // 火狐下，直接设img属性
                imgObjPreview.style.display = 'block';
                imgObjPreview.style.width = 'auto';
                imgObjPreview.style.height = 'auto';
                // imgObjPreview.src = docObj.files[0].getAsDataURL();

                // 火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
                imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);

            } else {
                // IE下，使用滤镜
                docObj.select();
                docObj.blur();
                var imgSrc = document.selection.createRange().text;
                var localImagId = document.getElementById("localImag");

                // 必须设置初始大小
                localImagId.style.width = "auto";
                localImagId.style.height = "auto";
                // 图片异常的捕捉，防止用户修改后缀来伪造图片
                try {
                    document.selection.empty();
                    localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                    localImagId.filters
                            .item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
                } catch (e) {
                    alert("您上传的图片格式不正确，请重新选择!");
                    return false;
                }
                imgObjPreview.style.display = 'none';
                document.selection.empty();
            }
            return true;
        }
         
	/*
	用途：检查输入手机号码是否正确
	输入：
	s：字符串
	返回：
	如果通过验证返回true,否则返回false
	*/
	function checkMobile() {
    	$("#telmessage").html("")
    	var s = $.trim($("#telphone").val());
    	if(s!=null&s.length>0){
		var regu = /^[1][0-9][0-9]{9}$/;
		var re = new RegExp(regu);
		if (re.test(s)) {
		return true;
		} else {
		 $("#telmessage").css("color","red");
                $("#telmessage").html("非法手机号");
		return false;
		}
    	}
	} 

		$(function(){
            toLoadBrand();
        });
        //加载所有品牌
        function toLoadBrand(){
            $.post("sys/toLoadBrandCarInfoAction.action",function(data){
                for(var i=0;i<data.length;i++){
                    $("#brand").append("<option name='' value='"+data[i].type_id+"'>"+data[i].type_name+"</option>");
                    
                }
            })
        };
        
        //加载所有类型
        function toLoadType(){
            
            var bra_id = $("#brand").val();
            $("#type").html("<option value=''>请选择类型</option>");
            
            $.post("sys/toLoadTypeCarInfoAction.action",{bra_id:bra_id},function(data){
                for(var i=0;i<data.length;i++){
                    $("#type").append("<option  value='"+data[i].type_id+"'>"+data[i].type_name+"</option>");
                }
            })
        };
        
		
    </script>
</head>

<body>

    <div class="mformbody" >
        <form action="sys/updateCarInfoAction.action" name="carForm" method="post" enctype="multipart/form-data">
	        <ul class="mforminfo">
	            <li><label>登记人的姓名</label><input name="carInfo.create_user_name" id="create_user_name" type="text" class="mdfinput" readonly="readonly" value="${session_user.user_name}" /></li>
	            <li><label>车牌号</label><input name="carInfo.car_code" id="car_code" type="text" class="mdfinput" value="${carInfo.car_code}"/></li>
	            <li><label>车辆名称</label><input name="carInfo.car_name" id="car_name" type="text" class="mdfinput" value="${carInfo.car_name}" /></li>
	            <li><label>租借金额</label><input name="carInfo.rent_price" id="rent_price" type="text" class="mdfinput" value="${carInfo.rent_price}" /></li>
	            <li><label>车辆颜色</label><input name="carInfo.car_color" id="car_color" type="text" class="mdfinput"  value="${carInfo.car_color}"/></li>
	            <li><label>购买价格</label><input name="carInfo.buy_price" id="buy_price" type="text" class="mdfinput" value="${carInfo.buy_price}" /></li>
	            <li><label>车辆保证金</label><input name="carInfo.deposit" id="deposit" type="text" class="mdfinput" value="${carInfo.deposit}" /></li>
	            <li><label>汽车行驶公里数</label><input name="carInfo.km" id="km" type="text" class="mdfinput" value="${carInfo.km}" /></li>
	            <li><label>品牌</label>
	                <select id="brand" onchange="toLoadType()" class="mdfinput">
	                    <option value="">请选择品牌</option>
	                </select>
	            </li>
	            <li><label>类型</label>
	                <select id="type" name="carInfo.carType.type_id" class="mdfinput">
	                    <option value="">请选择类型</option>
	                    <option value="dasd" selected="selected">${carInfo.carType.type_name}</option>
	                </select>
	            </li>
	            <li style="width: 300px;height: 170px;"><label>图片</label>
	                <!-- 按钮相关标签结构在下面的a标签中-->
	                <a href="javascript:void(0);" style="display:block;width:100px;height:45px;position:relative;overflow:hidden;text-decoration:none;">
	                    <!--下面的按钮就是所看到的按钮-->
	                    <input type="button" class="uploadbtn" value="上传" >
	                    <!--下面的file标签设置为完全透明覆盖在上面的按钮上，用户点击按钮其实就是点击的file标签，为了让鼠标在按钮上所有浏览器都显示小手，我们必须把file标签的预览按钮定位到按钮上，而且要足够大;注意：这里千万别给file标签加width样式，否则你的小手样式将无法兼容所有浏览器-->
	                    <input type="file" name="photo" id="photo" style="height:45px;font-size:100px;position:absolute;cursor:pointer;top:0;right:0;filter:alpha(opacity=0);-moz-opacity:0;opacity:0;z-index:2;"  onchange="setImagePreview()">
	                </a>
	            </li>
	            <li style="width: 350px;height: 170px;">
	                <div id="localImag"><img id="preview" src="<%=path %>/upload/carinfo/<s:property value="carInfo.photo" default="guest.png"/>"/></div>
	            </li>
	            <li><label>车辆描述</label><textarea name="carInfo.car_desc" id="car_desc" cols="" rows="" class="textinput">${carInfo.car_desc}</textarea></li>
	            <li><label>&nbsp;</label><input  type="submit" class="mbtn" value="确认更新"/></li>
	            
	        </ul>
	        <input name="carInfo.car_id" type="hidden" value="${carInfo.car_id}"/>
        </form>
    </div>
</body>

</html>