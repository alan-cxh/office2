<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>职位变更记录列表</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <script type="text/javascript">
    	function showDetail(tId) {
				tId = encodeURI(tId);
				var url = "positionManage_showDetailUI.action?id=" + tId + "&t=" + new Date();
				window.showModalDialog(url, null,
						"dialogHeight:400px;dialogWidth:660px;resizable:yes");
		}
    </script>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 职位变更记录管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
<!--按指定条件查询-->
	<s:form action="positionManage_list" method="post" id="pageForm">
		<div id="TableTail" style="margin-left: -50px"> 
			<div id="TableTail_inside">
				<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left" style="padding-bottom: 10px">
					<tr valign="bottom" style="padding-bottom: 10px">
						<td></td>
						<td>
							现在部门：
							<s:select name="VnewDepart"
	                        		list="#departmentList" listKey="name" listValue="name"
	                        		headerKey="" headerValue="====请选择部门===="
	                        />
	                        
	                       	 变更前部门：
							<s:select name="VoldDepart"
	                        		list="#departmentList" listKey="name" listValue="name"
	                        		headerKey="" headerValue="====请选择部门===="
	                        />
	                        
							姓名：
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
            	<td width="10%">变更人员</td>
				<td width="25%">角色</td>
				<td width="20%">部门</td>
				<td width="20%">职位变更时间</td>
				<td width="25%">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        <s:if test="count == 0">
        	<td align="center" colspan="5"><font color="red" size="4">没有职位变更的相关记录</font></td>
        </s:if>
        <s:else>
        <s:iterator value="recordList" status="status">
			<tr class="TableDetail1 template">
				<!-- 点击该部门显示其子部门 -->
				<td align="center">${user.name}&nbsp;</td>
				<td align="center">${newRole}&nbsp;</td>
				<td align="center">${newDepartment}&nbsp;</td>
				<td align="center">${changeTime}&nbsp;</td>
				<td align="center">&nbsp;
					<s:a action="positionManage_delete?id=%{id}" onClick="return confirm('您确定要删除本条记录吗?')">删除</s:a>&nbsp;
					<a href="javascript:showDetail('${id}')">查看详细信息</a>
				</td>
			</tr>
		</s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div style="height: 3px;background-color: #4386B7"></div>
<!--分页信息-->
<%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %>  
<!--说明-->	
<div id="Description"> 
	<font size="3" color="red">说明：</font><br><br/>
	1、点击“查看详细信息”可以查看该职位变更记录相关的详细信息。<br/><br/>
</div>

</body>
</html>
