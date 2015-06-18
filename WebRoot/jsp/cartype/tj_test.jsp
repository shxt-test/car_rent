<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/resource/fusioncharts/fusioncharts.js"></script><!--引入主类库-->

</head>
<body>
<div id="mzl" style="height: 700px;">在这加载图形</div><!--定义一个div用于存放图形-->
	<script type="text/javascript">
	    //json版
		//var json= '{"chart": {"yaxisname": "Sales Figure","caption": "Top 5 Sales Person","numberprefix": "$","useroundedges": "1","bgcolor": "FFFFFF,FFFFFF","showborder": "0"},"data": [{"label": "Alex","value": "25000"},{"label": "Mark","value": "35000"},{"label": "David","value": "42300"},{"label": "Graham","value": "35300"},{"label": "John","value": "31300"}]}'; 
		//var chart = new FusionCharts("Column3D.swf", "ChartId", "650", "300", "0", "0");
		//创建一个FusionCharts对象，第一个参数为swf文件的路径，第二个为id用来标识这个对象，第三个为宽度，第四个为高度
		//chart.setDataURL("data/data.xml");//xml文件
		//  chart.setDataXML(xmldata);
		//chart.setJSONData(json);
		//chart.render("mzl");//将FusionCharts对象填充到指定的div标签处：render（div）
		
	   /*xml版
	   $.post("/sys/tjDataXMLUserAction.action",function(xml){
		   var chart = new FusionCharts("Column3D.swf", "ChartId", "650", "450", "0", "0");
		   chart.setDataXML(xml);
		   chart.render("mzl");
	   });*/
	    
	   $.post("<%=path%>/sys/tjDataJSONCarTypeAction.action",function(json){
           var chart = new FusionCharts("Pyramid.swf", "ChartId", "650", "450", "0", "0");
            chart.setJSONData(json);
            chart.render("mzl");
       });
	</script>
</body>

</html>
