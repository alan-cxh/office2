<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" />
<title>无权限访问</title>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/myCSS/404.css" />

</head>
<body>

<div id="wrap">
	<div align="center" style="margin-top: 55%">
		<font face="行书" color="white" size="6">对不起，您没有访问权限！</font>
	</div>
	<div id="text" align="center">
		<strong>
			<a onclick="javascript:history.go(-1);">返回上一页</a> 
		</strong>
	</div>
</div>

<div class="animate below"></div>
<div class="animate above"></div>
<div style="text-align:center;">
</div>
</body>
</html>