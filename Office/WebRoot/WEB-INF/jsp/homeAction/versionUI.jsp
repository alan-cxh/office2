<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>版本基本信息</title>
<style type="text/css">
	body {
		background-color: #F3F9FD
	}
	td {
		font-style: normal;
		font-size:11pt;
		font-family:宋体,sans-serif;
		color: #07507B;
	}
</style>
</head>

<body>
<!-- 表单内容显示 -->
<div id="box" style="margin-left: 20px">
	<table width="580px" height="280px">
		<tr height="45px">
			<td colspan="2" align="center"><font color="#085076" size="5">版本信息</font></td>
		</tr>
		<tr>
			<td width="20%">Version:</td>
			<td>试用版</td>
		</tr>
		<tr>
			<td width="20%">邮箱：</td>
			<td>1142787827@qq.com</td>
		</tr>
		<tr>
			<td width="20%">版权所有：</td>
			<td>Copyright &copy; 
			2015/10 - <%=new SimpleDateFormat("yyyy/MM").format(new Date())%> 贵州民族大学<br>
			信息工程学院  | 学生开放实验室维护
		</tr>
	</table>
</div>
</body>
</html>