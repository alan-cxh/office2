<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>TopPage</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/top.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/myScript/clock.js"></script>
</head>

<body class="PageBody" style="margin: 0">
 
	<div id="Head1">
		<div id="Logo">
			<a id="msgLink" href="javascript:void(0)"></a>
            <font color="#0000CC" style="color:#FFFFFF; font-size:28px; font-family:Arial Black, Arial"><b>信息工程学院办公自动化系统</b></font>
        </div>
		
		<div id="Head1Right">
			<div id="Head1Right_UserName">
                <img border="0" width="13" height="14" src="${pageContext.request.contextPath}/style/images/top/user.gif" /> 您好:<b>${user.name}</b>
			</div>
			<div id="Head1Right_UserDept"></div>
			
			<div id="Head1Right_Time"></div>
		</div>
		
        <div id="Head1Right_SystemButton">
            <a href="${pageContext.request.contextPath}/user_logout.action" onclick="return window.confirm('您确定要退出本次登录吗?')" target="_parent">
				<img width="78" height="20" alt="退出系统" src="${pageContext.request.contextPath}/style/blue/images/top/logout.gif" />
			</a>
        </div>
		
        <div id="Head1Right_Button">
            
        </div>
	</div>
    
    <div id="Head2">
        <div id="Head2_Awoke">
            <ul id="AwokeNum">
                <li><a target="desktop">
						<img border="0" width="11" height="13" src="${pageContext.request.contextPath}/style/images/top/msg.gif" /> 消息
						<span id="msg"></span>
					</a>
				</li>
                <li class="Line"></li>
                <li><a target="_blank" href="http://mail.gzmu.edu.cn/">
						<img border="0" width="16" height="11" src="${pageContext.request.contextPath}/style/images/top/mail.gif" /> 邮件收发
						<span id="mail"></span>
					</a>
				</li>
                <li class="Line"></li>
				  <!-- 是否有待审批文档的提示1，数量 -->
                <li><s:a action="flow_myTaskList" target="right">
                		<img border="0" width="12" height="14" src="${pageContext.request.contextPath}/style/images/top/wait.gif" /> 
                		待办事项(<span id="wait" class="taskListSize">${taskCount}</span>)
                	</s:a>
                </li>
				
                <!-- 是否有待审批文档的提示，提示审批 -->
                <li id="messageArea"></li>
                
                <li class="Line"></li>
                <li id="clock" style="color: #FFF;font-size: 15px;">
					<script type="text/javascript">
						var clock = new Clock();
						clock.display(document.getElementById("clock"));
					</script>
				</li>
                <li class="Line"></li>
            </ul>
        </div>
        
		<div id="Head2_FunctionList" style="padding-bottom: 3px">
			<!-- 刷新嵌套的主页面 -->
        	<a href="javascript: window.parent.right.location.reload(true);"><img width="20px" height="20px" alt="刷新" src="${pageContext.request.contextPath}/style/images/myImages/suaxin.png"> </a>
        </div>
	</div>
</body>
</html>
