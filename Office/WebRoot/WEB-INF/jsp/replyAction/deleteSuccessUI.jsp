<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" />
<title>无权限访问</title>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/myCSS/404.css" />
<SCRIPT language=javascript>
	function go(){
		window.history.go(-1);
	}
	setTimeout("go()",3000);
</SCRIPT>
</head>
<body>

<div id="wrap">
	<div align="center" style="margin-top: 55%">
		<font face="行书" color="white" size="3">删除数据成功！</font>
	</div>
	<div id="text" align="center">
		<strong>
			<a href="javascript:history.go(-1)">返回上一页</a> 
		</strong>
	</div>
</div>

<div class="animate below"></div>
<div class="animate above"></div>
<div style="text-align:center;">
</div>
</body>
</html>