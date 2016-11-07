<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" />
<title>404错误</title>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/myCSS/404.css" />

</head>
<body>

<div id="wrap">
	<div align="center">
		<img src="${pageContext.request.contextPath}/style/images/404.png" alt="404" />
	</div>
	<div id="text">
		<strong>
			<span></span>
			<a href="${pageContext.request.contextPath}/home_index.action">返回首页</a>
			<a href="javascript:history.back()">返回上一页</a>
		</strong>
	</div>
</div>

<div class="animate below"></div>
<div class="animate above"></div>
<div style="text-align:center;">
</div>
</body>
</html>