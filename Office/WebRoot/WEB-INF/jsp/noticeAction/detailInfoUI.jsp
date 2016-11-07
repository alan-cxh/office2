<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公告详细信息</title>
<style type="text/css">
	body {
		background-color: #F3F9FD
	}
	td {
		font-style: normal;
		font-size:11pt;
		font-family:黑体,sans-serif;
		color: #07507B;
	}
</style>
</head>


<body>
<!-- 表单内容显示 -->
<div id="box">
	<table width="90%" height="90%" style="margin-top: 15px">
		<tr height="45px">
			<td colspan="2" align="center"><font color="#085076" size="5" face="黑体">${title}</font></td>
		</tr>
		<tr>
			<td colspan="2" valign="top" style="font-size: 13pt;color: #9B495D;font-family: 宋体;line-height:30px;">　　${content}</td>
		</tr>
		<tr height="25px">
			<td align="right">发  布  者：</td>
			<td width="30%"> ${publishUser.name}</td>
		</tr>
		<tr height="25px">
			<td align="right">发布时间：</td>
			<td width="30%"> ${publishDate}</td>
		</tr>
		
	</table>
</div>
</body>
</html>