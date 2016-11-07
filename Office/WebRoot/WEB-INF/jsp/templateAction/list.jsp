<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>表单模板列表</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 模板管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
<!--按指定条件查询-->
	<s:form action="template_list" method="post" id="pageForm">
		<div id="TableTail" style="margin-left: -50px"> 
			<div id="TableTail_inside">
				<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left" style="padding-bottom: 10px">
					<tr valign="bottom" style="padding-bottom: 10px">
						<td></td>
						<td>
							模板名称：
							<s:textfield name="Vname"></s:textfield>
							<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG" align="ABSMIDDLE"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="30%" align="center">模板名称</td>
				<td width="30%" align="center">所用流程</td>
                <td align="center">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="documentTemplateList">
        
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td align="center">${name}&nbsp;</td>
				<td align="center">${processKey}&nbsp;</td>
				<td align="center">
					<s:a action="template_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>&nbsp;
					<s:a action="template_editUI?id=%{id}">修改</s:a>&nbsp;
					<s:a action="template_download?id=%{id}">下载</s:a>
				</td>
			</tr>
		</s:iterator>
		
        </tbody>
    </table>
    
	<!-- 其他功能超链接 -->
	<div id="TableTail">
	    <div id="TableTail_inside">
			<s:a action="template_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png"/></s:a>
	    </div>
	</div>
</div>
<!--分页信息-->
<%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %> 

<div class="description">
	<font size="3" color="red">说明：</font><br><br />
	1，删除时，相应的文件也被删除。<br /><br />
	2，下载时，文件名默认为：{表单模板名称}.doc<br />
</div>
</body>
</html>
    