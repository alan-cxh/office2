<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <link href="${pageContext.request.contextPath}/fusionCharts/fusionChartView.css" type=text/css rel=stylesheet/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/fusionCharts/FusionCharts.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/fusionCharts/FusionChartsExportComponent.js"></script> 
</head>
<body>

<div style="float: left; width: 180px; height: 100%; padding-top: 30px; font-size: 15px;background-color: #F3F9FD" align="center">
	<!-- 左边相同的部分 -->
	<%@ include file="/WEB-INF/jsp/DC/DC_humanResource/public.jsp"%>
</div>
<div style="float: left; width: 1px; height: 100%; background-color: #8EC4E9">
</div>


<div>
   <!-- 后台获取数据 -->
   <input type="hidden" value="<s:property value='xml'/>" id="xml"/>
   <div id="chartDiv" align="center" style="padding-top: 5%">部门-奖惩统计图</div>
      <script type="text/javascript">
      	  var flag = "1";
          var chart = new FusionCharts("fusionCharts/Charts/MSColumn3D.swf", "Column3D", "450", "400", "0", "0");
          var xml = $("#xml").val();
          chart.setDataXML(xml);
          chart.render("chartDiv");
          
          function change(){
			  if(flag == 1){
			  	 StackedColumn3D();
			  	 flag = 2;
			  }
			  else if(flag == 2){
			  	 ScrollArea2D();
			  	 flag = 3;
			  }
			  else if(flag == 3){
			  	 MSBar3D();
			  	 flag = 4;
			  }
			  else if(flag == 4){
			  	 MSColumn3D();
			  	 flag = 1;
			  }
		  }
		  
		  function MSColumn3D(){
			  var chart = new FusionCharts("fusionCharts/Charts/MSColumn3D.swf", "1", "450", "400", "0", "0");
		      var xml = $("#xml").val();
		      chart.setDataXML(xml);
		      chart.render("chartDiv");
		  }
		  
		  function ScrollArea2D(){
			  var chart = new FusionCharts("fusionCharts/Charts/ScrollArea2D.swf", "2", "450", "400", "0", "0");
		      var xml = $("#xml").val();
		      chart.setDataXML(xml);
		      chart.render("chartDiv");
		  }
		  function StackedColumn3D(){
			  var chart = new FusionCharts("fusionCharts/Charts/StackedColumn3D.swf", "Column3D", "450", "400", "0", "0");
		      var xml = $("#xml").val();
		      chart.setDataXML(xml);
		      chart.render("chartDiv");
		  }
		  
		  function MSBar3D(){
			  var chart = new FusionCharts("fusionCharts/Charts/StackedBar3D.swf", "Pie3D", "450", "400", "0", "0");
		      var xml = $("#xml").val();
		      chart.setDataXML(xml);
		      chart.render("chartDiv");
		  }
		  
      </script>
      <center style="padding-top: 30px"><input type='button' value="点击切换形状" onClick="change()"></center>
</div>
      
</body>
</html>
