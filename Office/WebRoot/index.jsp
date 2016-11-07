<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	//index.jsp页面的时候重定向到home_index.action
	response.sendRedirect(request.getContextPath()+"/home_index.action");
%>


