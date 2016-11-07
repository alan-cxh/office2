<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>审批流程列表</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <script type="text/javascript">
	    function showProcessImage( pdId ){
	    	pdId = encodeURI(pdId); // 进行URL编码
            var url = "processDefinition_showProcessImage.action?id=" + pdId + "&t=" + new Date();
            window.showModalDialog(url, null, "dialogHeight:550px, dialogWidth:500px;resizable:yes");
        }
    </script> 
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 审批流程管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
				<td width="10%">部署编号</td>
            	<td width="20%">ID</td>
				<td width="10%">KEY</td>
            	<td width="20%">流程名称</td>
				<td width="10%">最新版本</td>
                <td width="50%">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        
        <s:iterator value="pdList">
			<tr class="TableDetail1 template">
				<td align="center">${deploymentId}&nbsp;</td>
				<td align="center">${id}&nbsp;</td>
				<td align="center">${key}&nbsp;</td>
				<td align="center">${name}&nbsp;</td>
				<td align="center">${version}&nbsp;</td>
				<td align="center">
					<s:a action="processDefinition_delete" onclick="return delConfirm()">
						<s:param name="key" value="%{key}"></s:param>
						删除
					</s:a>&nbsp;
					<a href="javascript: showProcessImage( '${id}' )">查看流程图</a>
				</td>
			</tr>
        </s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="processDefinition_addUI"><img src="${pageContext.request.contextPath}/style/images/deploy.png" /></s:a>
        </div>
    </div>
</div>
<!--分页信息  不能分页-->

<div class="Description">
	<font size="3" color="red">说明：</font><br><br />
	1、列表显示的是所有流程定义（不同KEY）的最新版本。<br /><br />
	2、删除流程定义时，此Key的所有版本的流程定义都会被删除。<br /><br />
	3、查看流程图时显示的这个最新版本的流程定义的流程图片。<br />
</div>

</body>
</html>
	