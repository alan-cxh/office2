<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>申请查询</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 所有申请申请记录管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>


<div id="QueryArea">
	<div style="height: 30px">
		<!-- pageView根据id="pageForm"这个表单里面的pageNum属性 传到后台进行页面的跳转-->
		<s:form id="pageForm" action="application_list">
			<table border=0 cellspacing=3 cellpadding=5>
				<tr>
				<td>
				           模板：
					<s:select name="templateId" cssClass="SelectStyle"
						list="templateList" listKey="id" listValue="name"
						headerKey="" headerValue="======查看全部模板======"
					/>
					状态：
					<s:select name="status" cssClass="SelectStyle"
							list="{'审批中', '未通过', '已通过'}" 
							headerKey="" headerValue="======查看全部状态======"
					/>
				</td>
				<td><a href=""><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></a></td>
				</tr>
			</table>
		</s:form>
		
	</div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
				<td width="25%">标题</td>
				<td width="15%">申请人</td>
				<td width="18%">申请日期</td>
				<td width="10%">当前状态</td>
				<td>相关操作</td>
			</tr>
		</thead>	
				
		<!--显示数据列表：正在审批或审批完成的表单显示示例-->
        <tbody id="TableData" class="dataContainer" datakey="formList">
        
		<!-- 正在审批或审批完成的表单显示示例 -->
		
		<s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td align="center">${title}</td>
				<td align="center">${applicant.name}&nbsp;</td>
				<td align="center">${applyTime}&nbsp;</td>
				<td align="center">${status}&nbsp;</td>
				<td align="center">
					<s:a action="application_delete?id=%{id}" onClick="return window.delConfirm()">删除</s:a>&nbsp;
					<s:a action="flow_applicationInfo?applicationId=%{id}">查看申请信息</s:a>&nbsp;
					<s:a action="flow_approvedHistory?applicationId=%{id}">查看流转记录</s:a>
				</td>
			</tr>
		</s:iterator>	
			
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail"><div id="TableTail_inside"></div></div>
</div>

<!--分页信息-->
<%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %>


<div class="Description">
	<font size="3" color="red">说明：</font><br><br/>
	1、本页面列出了所有的申请记录的列表<br><br>
	2、对于申请记录的操作只有删除，当所部署的流程定义、所属模板、流程实例结束了，该申请记录不再有意义，便可以删除。<br><br>
	3、排序是：按申请时间降序排列（最后的申请在最前面）。<br><br>
</div>


</body>
</html>
	