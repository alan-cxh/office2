<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>查看个人信息</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 个人信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form>
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
        	 个人详细信息：
        	</DIV>  
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm" style="font-size: 50px">
					<tr height="30px">
                        <td width="150">姓　　名:</td>
                        <td>${name}</td>
                    </tr>
                    <tr height="30px">
                        <td>登 录 名:</td>
                        <td>${loginName}</td>
                    </tr>
					<tr height="30px">
                        <td>性　　别:</td>
                        <td>${gender}</td>
                    </tr>
                    <tr height="30px">
                        <td>出生日期:</td>
                        <td>${birthday}</td>
                    </tr>
					<tr height="30px">
                        <td>电话号码:</td>
                        <td>${phoneNumber}</td>
                    </tr>
					<tr height="30px">
                        <td>电子邮件:</td>
                        <td>${email}</td>
                    </tr>
                    <tr height="30px">
                        <td>状　　态:</td>
                        <td>正常</td>
                    </tr>
                    <tr height="30px">
                        <td>所属部门:</td>
                        <td>${department.name}</td>
                    </tr>
                    <tr height="30px">
                        <td>扮演角色:</td>
                        <s:iterator value="roles">
                      	  <td>${name}</td>
                        </s:iterator>
                    </tr>
                </table>
            </div>
        </div>
    </s:form>
</div>

<!-- 说明 -->
<div class="Description">
	<font size="3" color="red">说明：</font><br><br/>
	1、本页面只能查看个人详细信息<br/><br/>
	2、若要修改个人信息，请在"维护个人信息"中进行修改<br/><br/>
</div>

</body>
</html>



