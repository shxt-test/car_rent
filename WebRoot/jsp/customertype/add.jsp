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
    <title> | </title>
    <link href="<%=path%>/resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <!-- 引入JQuery支持的库 -->
    <script type="text/javascript" src="<%=path%>/resource/admin/js/jquery.js"></script>
    <!-- 引入artDailog支持的库 -->
    <link rel="stylesheet" href="<%=path%>/resource/admin/artDialog/css/ui-dialog.css">
    <script type="text/javascript" src="<%=path%>/resource/admin/artDialog/dist/dialog-plus.js"></script>
    <script type="text/javascript">
       function toSub(){
            $.post("sys/addCustomerTypeAction.action",$("#customertypeForm").serialize(),function(data){
                
                if(data.flag=="success"){
                         var dialog = top.dialog.get(window);
                          dialog.close(data.message);
                          dialog.remove();
                          
                }else{
                    alert(data.message);
                    return false;
                }
            });
        }
        
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
    </script>
</head>

<body>
    <div class="formbody">
    <form  id="customertypeForm" enctype="multipart/form-data">
    <ul class="forminfo">
        <li><label>类型名称</label><input name="customerType.c_type_name" type="text" class="dfinput"/><i>名称不能超过30个字符</i></li>
        <li><label>打折率</label><input name="customerType.c_discount" type="text" class="dfinput"/><i>多个关键字用,隔开</i></li>
         <li style="width: 300px;height: 170px;"><label>头像</label>
                <!-- 按钮相关标签结构在下面的a标签中-->
                <a href="javascript:void(0);" style="display:block;width:100px;height:45px;position:relative;overflow:hidden;text-decoration:none;">
                    <!--下面的按钮就是所看到的按钮-->
                    <input type="button" class="uploadbtn" value="上传" >
                    <!--下面的file标签设置为完全透明覆盖在上面的按钮上，用户点击按钮其实就是点击的file标签，为了让鼠标在按钮上所有浏览器都显示小手，我们必须把file标签的预览按钮定位到按钮上，而且要足够大;注意：这里千万别给file标签加width样式，否则你的小手样式将无法兼容所有浏览器-->
                    <input type="file" name="photo" id="photo" style="height:45px;font-size:100px;position:absolute;cursor:pointer;top:0;right:0;filter:alpha(opacity=0);-moz-opacity:0;opacity:0;z-index:2;"  onchange="setImagePreview()">
                </a>
            </li>
            <li style="width: 350px;height: 170px;">
                <div id="localImag"><img id="preview" src="<%=path%>/upload/user/guest.png"/></div>
            </li>
    <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存" onclick="toSub();"/></li>
    </ul>
    </form>

</div>


</body>

</html>