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
  
   <div id="chartDiv" align="center" style="padding-top: 5%">奖惩人数统计图</div>
      <script type="text/javascript">
      	  var flag = "1";
          var chart = new FusionCharts("fusionCharts/Charts/Column3D.swf", "Column3D", "450", "400", "0", "0");
          var xml = $("#xml").val();
          chart.setDataXML(xml);
          chart.render("chartDiv"); 
          
          function change(){
			  if(flag == 1){
			  	 Line();
			  	 flag = 2;
			  }
			  else if(flag == 2){
			  	 Doughnut3D();
			  	 flag = 3;
			  }
			  else if(flag == 3){
			  	 Bar2D();
			  	 flag = 4;
			  }
			  else if(flag == 4){
			  	 Pie3D();
			  	 flag = 5;
			  }
			  else if(flag == 5){
			  	 Column3D();
			  	 flag = 1;
			  }
		  }
		  
		  function Line(){
			  var chart = new FusionCharts("fusionCharts/Charts/Line.swf", "Line", "450", "400", "0", "0");
		      var xml = $("#xml").val();
		      chart.setDataXML(xml);
		      chart.render("chartDiv");
		  }
		  
		  function Bar2D(){
			  var chart = new FusionCharts("fusionCharts/Charts/Bar2D.swf", "Bar2D", "450", "400", "0", "0");
		      var xml = $("#xml").val();
		      chart.setDataXML(xml);
		      chart.render("chartDiv");
		  }
		  function Column3D(){
			  var chart = new FusionCharts("fusionCharts/Charts/Column3D.swf", "Column3D", "450", "400", "0", "0");
		      var xml = $("#xml").val();
		      chart.setDataXML(xml);
		      chart.render("chartDiv");
		  }
		  
		  function Pie3D(){
			  var chart = new FusionCharts("fusionCharts/Charts/Pie3D.swf", "Pie3D", "450", "400", "0", "0");
		      var xml = $("#xml").val();
		      chart.setDataXML(xml);
		      chart.render("chartDiv");
		  }
		  
		  function Doughnut3D(){
			  var chart = new FusionCharts("fusionCharts/Charts/Doughnut3D.swf", "Doughnut3D", "450", "400", "0", "0");
		      var xml = $("#xml").val();
		      chart.setDataXML(xml);
		      chart.render("chartDiv");
		  }
      </script>
      <center style="padding-top: 30px"><input type='button' value="点击切换形状" onClick="change()"></center>
</div>
      
</body>
</html>
