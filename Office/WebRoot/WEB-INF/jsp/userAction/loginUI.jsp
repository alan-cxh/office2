<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>用户登录</title>
	<meta content="">
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<link href="${pageContext.request.contextPath}/style/blue/login.css" type=text/css rel=stylesheet>
	<script type="text/javascript">
		$(function(){
			document.forms[0].loginName.focus();
		});
		
		// 在被嵌套时就刷新上级窗口
		if(window.parent != window){
			window.parent.location.reload(true);
		}
		
		//点击图片刷新验证码
		function validate(){
			var myimg = document.getElementById("code");
			now = new Date();
			myimg.src = "code?time=" + now.getTime();
		}
		
	</script>
</head>

<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 style="background-color: #145C8B">

<!-- 显示表单 -->

<s:form action="user_login" focusElement="loginNameInput" onsubmit="return isValid_loginUI(this);">
    <div id="CenterAreaBg" style="height: 100%"> 
        <div id="CenterArea">
            <div id="LogoImg"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/gzmu.png" width="150px" height="60px"/></div>
            <div id="LoginInfo">
                <table BORDER=0 CELLSPACING=0 CELLPADDING=0 width=100% border="1">
                	
                    <tr>
                        <td width=45 class="Subject"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/userId.gif" /></td>
                        <td>
                        	<s:textfield name="loginName" size="20" tabindex="1" Class="TextField" id="loginNameInput" placeholder="userName"/>
                        </td>
                        <td rowspan="2" style="padding-left:10px;">
                        	<input type="image" tabindex="4" src="${pageContext.request.contextPath}/style/blue/images/login/userLogin_button.gif" />
                        </td>
                    </tr>
                    <tr>
                        <td class="Subject"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/password.gif" /></td>
                        <td><s:password name="password" size="20" tabindex="2" showPassword="true" Class="TextField" placeholder="******"/></td>
                    </tr>
                    
                    <tr>
                        <td class="Subject"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/imgCode.gif" /></td>
                        <td><s:textfield name="code" size="20" tabindex="3" Class="TextField" value=""/></td>
                        <td style="padding-left: 10px"><img alt="验证码" src="code" onclick="validate()" id="code"/></td>
                    </tr>
                    
                    <tr>
                		<td colspan="3" align="center"><!-- 显示错误 -->
							<font color="red"><s:fielderror name="loginError"/></font>
                		</td>
                	</tr>
                </table>
            </div>
            <div id="CopyRight">
            <span>Copyright &copy; 2015/10 - <%=new SimpleDateFormat("yyyy/MM").format(new Date()) %>
            <a href="http://iec.gzmu.edu.cn" title ="信息工程学院" target=_blank >信息工程学院</a></span></div>
        </div>
    </div>
    </s:form>
</body>
</html>
