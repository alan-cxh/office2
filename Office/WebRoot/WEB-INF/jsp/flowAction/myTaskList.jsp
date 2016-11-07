<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>待我审批</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
</head>
<body> 

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 待我审批
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">

    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
				<td width="25%">标题</td>
				<td width="20%">申请人</td>
				<td width="25%">申请日期</td>
				<td>相关操作</td>
			</tr>
		</thead>		
		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="formList">
		
		<s:iterator value="#taskViewList">
			<tr class="TableDetail1 template">
				<td align="center">${application.title}</td>
				<td align="center">${application.applicant.name}&nbsp;</td>
				<td align="center"><s:date name="application.applyTime" format="yyyy-MM-dd HH:mm"/>&nbsp;</td>
				<td align="center">
					<s:a action="flow_approveUI?taskId=%{task.id}&applicationId=%{application.id}">审批处理</s:a>&nbsp;
					<s:a action="flow_approvedHistory?applicationId=%{application.id}">查看流转记录</s:a>
				</td>
			</tr>
		</s:iterator>
			
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail"><div id="TableTail_inside"></div></div>
</div>

<!--分页信息-->


<div class="Description">
<font size="3" color="red">说明：</font><br><br/>
	1、这里列出的所有的表单状态都为"审批中"。<br><br>
	2、标题的格式为：模板名称_申请人姓名_申请时间<br><br>
</div>

</body>
</html>
	