<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>职位变更查看</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
</head>
<body> 

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 职位变更详细信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
    <s:form>
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
        	 个人基本信息
        	</DIV>  
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">姓名</td>
                        <td><s:textfield name="name" class="InputStyle" readonly="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100">性别</td>
                        <td><s:label name="gender"></s:label></td>
                    </tr>
                    <tr>
                        <td width="100">职称</td>
                        <td><s:textfield name="jobTitle" class="InputStyle" readonly="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100">生日</td>
                        <td><s:textfield name="birthday" class="InputStyle Wdate" readonly="true"></s:textfield></td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
        	 调动前信息
        	</DIV>  
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">原单位名称</td>
                        <td><s:textfield name="originalDepartName" class="InputStyle" readonly="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100">原任职位</td>
                        <td><s:textfield name="originalRole" class="InputStyle" readonly="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100">辞职时间</td>
                        <td><s:textfield name="resignTime" class="InputStyle Wdate" readonly="true"></s:textfield></td>
                    </tr>
                </table>
            </div>
        </div>
        
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
        	 调动后信息
        	</DIV>  
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">现单位名称</td>
                        <td><s:textfield name="nowDepartName" class="InputStyle" readonly="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100">现任职位</td>
                        <td><s:textfield name="nowRole" class="InputStyle" readonly="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100">就业时间</td>
                        <td><s:textfield name="careerTime" class="InputStyle Wdate" readonly="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100" valign="top">职位变更原因</td>
                        <td><s:textarea name="reason" class="TextareaStyle" readonly="true"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
   </s:form>
</div>
</body>
</html>

