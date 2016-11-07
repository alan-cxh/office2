<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>MainPage</title>
	 <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<script type="text/javascript">
		javascript:window.history.forward(1);
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.cookie.js"></script>
</head>


<frameset rows="100,*,25" framespacing="0" border="0">
    <frame src="${pageContext.request.contextPath}/home_top.action" name="TopMenu"  scrolling="no" noresize />
    <frameset cols="180,*" id="resize">
        <frame noresize name="menu" src="${pageContext.request.contextPath}/home_left.action" scrolling="yes" />
        <frame noresize name="right" src="${pageContext.request.contextPath}/notice_noticeUI.action" scrolling="yes" />
    </frameset>
    <frame noresize name="status_bar" scrolling="no" src="${pageContext.request.contextPath}/home_bottom.action" />
</frameset>
<noframes> 
<body>
</body>
</html>

