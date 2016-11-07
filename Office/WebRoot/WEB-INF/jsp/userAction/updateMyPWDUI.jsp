<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>
	<title>登录密码修改</title>
   	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
   	<script type="text/javascript">
   		$(document).ready(function(){
   			$("#oldPassword").focus();
   		});
   		
   		$(function(){
   			$("#form").submit(function(){
	   			if($("#oldPassword").val() == ""){
	   				alert("请输入原密码！");
	   				$("#oldPassword").focus();
	   				return false;
	   			}
	   			if($("#newPassword").val() == ""){
	   				alert("请输入新密码！");
	   				$("#newPassword").focus();
	   				return false;
	   			}
	   			if($("#reNewPassword").val() == ""){
	   				alert("请再次输入新密码！");
	   				$("#reNewPassword").focus();
	   				return false;
	   			}
	   			if($("#newPassword").val() != $("#reNewPassword").val()){
	   				alert("两次输入密码不一样！");
	   				$("#reNewPassword").focus();
	   				return false;
	   			}else{
		   			if(window.confirm('您确定修改密码吗？修改成功后将重新登录系统！')){
	                	return true;
	              	}else{
	                    return false;
	                }
	   			}
   			});
   		});
   		
   	</script>
   
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 修改密码
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="user_updateMyPWD" id="form" method="post">
    <s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 登录密码修改 </DIV>
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                
                	<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							用户名：
						</td>
						<td><s:textfield name="name" cssClass="InputStyle" disabled="true"/></td>
					</tr>
					
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							登录名：
						</td>
						<td><s:textfield name="loginName" cssClass="InputStyle" disabled="true"/></td>
					</tr>
                	
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							请输入原密码：
						</td>
						<td><s:password name="oldPassword" id="oldPassword" cssClass="InputStyle"/></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							请输入新密码：
						</td>
						<td><s:password name="newPassword" id="newPassword" cssClass="InputStyle"/></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							再次输新密码：
						</td>
						<td><s:password name="reNewPassword" id="reNewPassword" cssClass="InputStyle"/></td>
					</tr>
					 <tr>
                		<td colspan="2" align=center><!-- 显示消息 ,密码修改正确或者错误信息-->
							<font color="red" size="4"><s:fielderror name="message"/></font>
                		</td>
                	</tr>
                </table>
            </div>
        </div>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>
<div class="Description">
<font size="3" color="red">验证规则：</font><br><br/>
	1、旧密码不能为空。<br/><br/>
	2、新密码不能为空。<br/><br/>
	3、再次输入的密码要和新密码一致。<br/><br>
	4、密码修改成功后将重新登录系统。<br/><br/>
</div>

</body>
</html>

