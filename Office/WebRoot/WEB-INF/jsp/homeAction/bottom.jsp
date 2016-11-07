<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="edu.iec.oa.util.SessionCounter" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>
    <title>BottomPage</title>
	<link href="${pageContext.request.contextPath}/style/blue/statusbar.css" type=text/css rel=stylesheet>
	<script type="text/javascript">
		function showVersion(){
			var url = "home_versionUI.action";
			window.showModalDialog(url,null,"dialogHeight:320px;dialogWidth:600px;resizable:yes");
		}
	</script>
</head>

<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>

<div id="StatusBar" style="width: 100%">
    <div id="Online">
    	在线人员：共 <span class="OnlineUser" id="onlineUserNum"><%=SessionCounter.getActiveSession() %></span> 人
    	<span class="OnlineView">
        	<a href="javascript:void(0)">[查看在线名单]</a>
        </span>
    </div>

    <div id="Info">
    	<a href="http://www.gzmu.edu.cn" title ="贵州民族大学" target=_blank >贵州民族大学&nbsp;&nbsp;&nbsp;</a>|
        <a href="http://iec.gzmu.edu.cn" title ="信息工程学院" target=_blank >&nbsp;&nbsp;&nbsp;信息工程学院</a>　|　　　　　　　　　　　　　　　　　　　　　
        Copyright &copy; 2015/10 - <%=new SimpleDateFormat("yyyy/MM").format(new Date()) %> 贵州民族大学信息工程学院　学生开放实验室维护
    </div>
    
    <DIV id=DesktopText>
    	<!-- 查看版本信息 -->
        <span id="Version">
        <a href="javascript:showVersion();">
           	<img border="0" width="11px" height="11px" src="${pageContext.request.contextPath}/style/images/top/help.gif" />
           	<img border="0" width="40px" height="11px" src="${pageContext.request.contextPath}/style/blue/images/top/version.gif" />
        </a>
        </span>
    </DIV>
</div>

</body>
</html>
