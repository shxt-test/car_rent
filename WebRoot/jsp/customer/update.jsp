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
         
       $(function(){
           
           
       })
       
     
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

        /*
        用途：检查输入对象的值是否符合E-Mail格式
        输入：str 输入的字符串
        返回：如果通过验证返回true,否则返回false
        */
        function isEmail(){
            var str = $.trim($("#email").val());
            var myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
            if (myReg.test(str)){ 

                return true;
            }
            return false;
        } 

            
            
        /*
        用途：检查输入字符串是否符合身份证格式并且判断性别和出生年月日
        输入：
        s：字符串
        返回：
        如果通过验证返回true,否则返回false
        */
    
        function isIDno() 
        {    
            $("#message").html("");
            $("#birthday").val("");
             var str = "";
            $("#sex").val(str);
            var strIDno = $.trim($("#id_card").val());
            if(strIDno!=null&strIDno.length>0){
            var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}; 
          
            var iSum = 0; 
            var info = ""; 
            //var strIDno = obj.value; 
            var idCardLength = strIDno.length;   
            if(!/^\d{17}(\d|x)$/i.test(strIDno)&&!/^\d{15}$/i.test(strIDno))  
            {
               $("#message").css("color","red");
                $("#message").html("非法省份证号");
                return false; 
            } 
          
            //在后面的运算中x相当于数字10,所以转换成a 
            strIDno = strIDno.replace(/x$/i,"a"); 
         
            if(aCity[parseInt(strIDno.substr(0,2))]==null) 
            { 
                $("#message").css("color","red");
                $("#message").html("非法地区");
                return false; 
            } 
             
            if (idCardLength==18) 
            { 
                sBirthday=strIDno.substr(6,4)+"-"+Number(strIDno.substr(10,2))+"-"+Number(strIDno.substr(12,2)); 
                var d = new Date(sBirthday.replace(/-/g,"/")) 
                if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())) 
                {
                $("#message").css("color","red");
                $("#message").html("非法生日");
                  
                    return false; 
                } 
         
                for(var i = 17;i>=0;i --) 
                    iSum += (Math.pow(2,i) % 11) * parseInt(strIDno.charAt(17 - i),11); 
         
                if(iSum%11!=1) 
                { 
                 $("#message").css("color","red");
                $("#message").html("非法省份证号");
                    return false; 
                } 
            } 
            else if (idCardLength==15) 
            { 
                sBirthday = "19" + strIDno.substr(6,2) + "-" + Number(strIDno.substr(8,2)) + "-" + Number(strIDno.substr(10,2)); 
                var d = new Date(sBirthday.replace(/-/g,"/")) 
                var dd = d.getFullYear().toString() + "-" + (d.getMonth()+1) + "-" + d.getDate();    
                if(sBirthday != dd) 
                { 
                $("#message").css("color","red");
                $("#message").html("非法生日");
                     
                    return false; 
                } 
            }         
                    var year = strIDno.substr(6, 4);
                    var month = strIDno.substr(10, 2);
                    var day = strIDno.substr(12, 2);
                    var birthday = year+"-"+month+"-"+day;
                    document.getElementById("birthday").value=birthday;
                    var sex = strIDno.substr(16, 1);
                   
                    
                    if(parseInt(sex)%2==1){
                       str = "男";
                    }else{
                        str = "女";
                    }
                    $("#sex").val(str);
            return true;
            }
        }


          
      

    </script>
</head>

<body>

    <div class="mformbody">
        <form action="sys/updateCustomerAction.action" name="cusInfoForm" method="post" >
        <ul class="mforminfo">
            <li><label>身份证号码</label><input name="cusInfo.cus_id_card" id="id_card" type="text" value="${cusInfo.cus_id_card}" class="mdfinput" style="width: 400px;" /><span id="message"></span></li>
            <li><label>姓名</label><input name="cusInfo.cus_name" id="cus_name" type="text" class="mdfinput" value="${cusInfo.cus_name}" /></li>
            <li><label>家庭住址</label><input name="cusInfo.cus_address" id="cus_address" type="text" class="mdfinput" value="${cusInfo.cus_address}" /></li>
            <li><label>工作单位</label><input name="cusInfo.cus_work_address" id=" cus_work_address" type="text" class="mdfinput" onblur="isEmail()" value="${cusInfo.cus_work_address}"/></li>
            <li><label>联系方式</label><input name="cusInfo.cus_tel" id="cus_tel" type="text" class="mdfinput" onblur="checkMobile()" value="${cusInfo.cus_tel}"/><span id="telmessage"></span></li>
            <li><label>驾驶证号码</label><input name="cusInfo.cus_driver_code" id="cus_driver_code" type="text" class="mdfinput" value="${cusInfo.cus_driver_code}" /></li>
            <li><label>性别</label>
           <s:select list="#{'男':'男','女':'女'}" id="cus_sex" cssClass="select_show" cssStyle="width: 150px;" headerKey="" headerValue="请选择性别"
            name="cusInfo.cus_sex"
            ></s:select>
            </li>
            <li><label>客户类型</label>
                <s:select name="cusInfo.customer_type.c_type_id" id="c_type_id" list="cusTypeList" listKey="c_type_id" listValue="c_type_name" cssClass="select_show" headerKey="" headerValue="请选择角色"></s:select>
            </li> 
        <li>---------------------------------------担保人信息-----------------------------------------------------</li>
            <li><label>身份证号码</label><input name="cusInfo.gua_id_card" id="gua_id_card" type="text" class="mdfinput" style="width: 400px;" onblur="isIDno()" value="${cusInfo.gua_id_card}"/><span id="message"></span></li>
            <li><label>姓名</label><input name="cusInfo.gua_name" id="gua_name" type="text" class="mdfinput" value="${cusInfo.gua_name}"/></li>
            <li><label>家庭住址</label><input name="cusInfo.gua_address" id="gua_address" type="text" class="mdfinput" value="${cusInfo.gua_address}"/></li>
            <li><label>工作单位</label><input name="cusInfo.gua_work_address" id=" gua_work_address" type="text" class="mdfinput" value="${cusInfo.cus_work_address}"/></li>
            <li><label>联系方式</label><input name="cusInfo.gua_tel" id="gua_tel" type="text" class="mdfinput" onblur="checkMobile()" value="${cusInfo.gua_tel}"/><span id="telmessage"></span></li>
            <li><label>与客户关系</label><input name="cusInfo.relation" id="relation" type="text" class="mdfinput" value="${cusInfo.relation}" /></li>
            </ul>
            <li><label>&nbsp;</label><input  type="submit" class="mbtn" value="确认保存"/></li>
            
            <s:hidden name="cusInfo.cus_id"></s:hidden>
        </form>

    </div>


</body>

</html>