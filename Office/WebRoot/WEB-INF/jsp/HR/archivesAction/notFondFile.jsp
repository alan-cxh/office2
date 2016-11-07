<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>文件不存在</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
</head>
<body>
 


<div>
    <table style="padding-top: 15%" align="center">
	    <tr>
	    	<td width="100%" align="center"><font size="5" color="red">没有上传文件或文件不存在，请联系管理员，谢谢...</font></td>
	    </tr>
	    <tr height="20px"></tr>
	    <tr>
	    	<td width="100%" align="center">
            	<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
	    	</td>
	    </tr>
    </table>
  
</div>
    
</body>
</html>