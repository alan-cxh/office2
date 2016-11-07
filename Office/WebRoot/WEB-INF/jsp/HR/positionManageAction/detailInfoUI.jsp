<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title></title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<script type="text/javascript">
	</script>
</head>
<body> 

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 职位变更记录详细信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
	<!-- 将修改页面和添加页面合并，通过三目运算符判断id是否为空来判断action是添加还是修改 id为空时添加-->
    <s:form>
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
        	 变更基本信息
        	</DIV>  
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100px">职工的姓名</td>
                        <td align="left"><s:textfield name="user.name" cssClass="InputStyle" disabled="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100px">以前的职位</td>
                        <td><s:textfield name="oldRole" cssClass="InputStyle" disabled="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100px">以前的部门</td>
                        <td><s:textfield name="oldDepartment" cssClass="InputStyle" disabled="true"></s:textfield></td>
                    </tr>
                    
                    <tr height="20px">
                    </tr>
                    
                    <tr>
                        <td width="100px">现在的职位</td>
                        <td><s:textfield name="newRole" cssClass="InputStyle" disabled="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100px">现在的部门</td>
                        <td><s:textfield name="newDepartment" cssClass="InputStyle" disabled="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100px">职位变更时间</td>
                        <td><s:textfield name="changeTime" cssClass="InputStyle Wdate" disabled="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100px" valign="top">职位变更原因</td>
                        <td><s:textarea name="reason" cssClass="TextareaStyle" disabled="true"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
   </s:form>
</div>
</body>
</html>

