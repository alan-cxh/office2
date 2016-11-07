<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>培训记录详细信息</title>
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 培训记录详细信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
    <s:form>
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
        	 培训相关信息
        	</DIV>  
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	 <tr>
                        <td width="100">职工姓名</td>
                        <td><s:textfield name="userName" cssClass="InputStyle" disabled="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100">开始时间</td>
                        <td><s:textfield name="startTime" cssClass="Wdate InputStyle" disabled="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100" valign="top">培训内容</td>
                        <td><s:textarea name="content" cssClass="TextareaStyle" disabled="true"></s:textarea></td>
                    </tr>
                    <tr>
                        <td width="100">培训机构</td>
                        <td><s:textfield name="department" cssClass="InputStyle" disabled="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100">培训地点</td>
                        <td><s:textfield name="address" cssClass="InputStyle" disabled="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100">结束时间</td>
                        <td><s:textfield name="endTime" cssClass="Wdate InputStyle" disabled="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100" valign="top">培训结果</td>
                        <td><s:textarea name="result" cssClass="TextareaStyle" disabled="true"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
   </s:form>
</div>
</body>
</html>

