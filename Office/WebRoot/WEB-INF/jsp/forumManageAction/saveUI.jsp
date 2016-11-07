<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>版块设置</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <script type="text/javascript"></script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 版块设置
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
    <s:form action="forumManage_%{id == null ? 'add' : 'update'}" onsubmit="return isValid_saveForumManageUI(this);">
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
        		<s:if test="%{id == null}">添加新版块</s:if>
        		<s:else>修改版块</s:else>
        	</DIV>
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">版块名称</td>
                        <td><s:textfield name="name" cssClass="InputStyle required"/> &nbsp;&nbsp;<font color="#A33639">*版块名称不能为空*</font></td>
                    </tr>
                    
                    <tr height="20px"></tr>
                    
                    <tr>
                        <td valign="top">版块说明</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"/></td>
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
	<font size="3" color="red">说明：</font><br><br />
	1、新添加的版块默认显示在最下面。<br />
</div>

</body>
</html>
