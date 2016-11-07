<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>职位更变列表</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <script type="text/javascript">
    	function showDetail(tId) {
				tId = encodeURI(tId);
				var url = "position_detailInfoUI.action?id=" + tId + "&time=" + new Date();
				window.showModalDialog(url, null,"dialogHeight:570px;dialogWidth:640px;resizable:yes");
		}
    </script>
</head>
<body>
 
<div id="Title_bar" data-role="page">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 职位变更
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">

<!--按指定条件查询-->
	<s:form action="position_list" method="post" id="pageForm">
		<div id="TableTail" style="margin-left: -50px"> 
			<div id="TableTail_inside">
				<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left" style="padding-bottom: 10px">
					<tr valign="bottom" style="padding-bottom: 10px">
						<td></td>
						<td>
							部门：
							<s:select name="departmentId"
	                        		list="#departmentList" listKey="id" listValue="name"
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
       
        <!--表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="10%">姓名</td>
                <td width="10%">联系电话</td>
                <td width="10%">所属部门</td>
                <td width="20%">角色</td>
                <td width="5%">性别</td>
                <td width="15%">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        <s:if test="count == 0">
        	<td align="center" colspan="5"><font color="red" size="4">没有职工可添加职位变更记录</font></td>
        </s:if>
        <s:else>
        	<s:iterator value="recordList">
        	<%--超级管理员是不能修改删除的 --%>
	       		<s:if test="%{loginName != 'admin'}">
	            <tr class="TableDetail1 template">
	                <td align="center">${name}&nbsp;</td>
	                <td align="center">${phoneNumber}&nbsp;</td>
	                <td align="center">${department.name}&nbsp;</td>
	                <td align="center">&nbsp;
	                	<s:iterator value="roles">${name}&nbsp;</s:iterator>
	                </td>
	                <td align="center">${gender}&nbsp;</td>
	                <td align="center">&nbsp;
	                    <s:a action="position_addUI?userId=%{id}">添加职位更变记录</s:a>&nbsp;
					</td>
	                </tr>
	            </s:if>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div style="height: 3px;background-color: #4386B7"></div>
<!--分页信息-->
<%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %>  
<!-- 说明 -->
<div class="Description">
	<font size="3" color="red">说明：</font><br><br />
	1、相关操作人员请根据用户更改部门、角色（职位）等信息后，再对该用户添加职位变更信息。<br/><br/>
	2、操作完毕后，相关职工的变更信息请到"系统管理"的"职位变更记录管理"进行管理或查看。<br/><br/>
</div>
</body>
</html>