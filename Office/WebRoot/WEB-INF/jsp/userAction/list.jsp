<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>用户列表</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
<!--按指定条件查询-->
	<s:form action="user_list" method="post" id="pageForm">
		<div id="TableTail" style="margin-left: -50px"> 
			<div id="TableTail_inside">
				<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left" style="padding-bottom: 10px">
					<tr valign="bottom" style="padding-bottom: 10px">
						<td></td>
						<td>
							部门：
							<s:select name="departmentId"
	                        		list="#departmentList" listKey="id" listValue="name"
	                        		headerKey="" headerValue="==请选择部门=="
	                        />
							职称：
							<s:select name="VjobTitle"
	                        		list="#jobTitleList" listKey="name" listValue="name"
	                        		headerKey="" headerValue="==请选择职称=="
	                        />
	                                                                        状态：
							<s:select name="Vstatus" list="#{null:'==状态==',2:'正常',1:'已停用'}"/>
							
							登录名：
							<s:textfield name="VloginName"></s:textfield>
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
                <td width="10%">登录名</td>
                <td width="10%">姓名</td>
                <td width="10%">联系电话</td>
                <td width="10%">所属部门</td>
                <td width="20%">角色</td>
                <td width="10%">职称</td>
                <td width="5%">状态</td>
                <td width="20%">相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
       
        <s:iterator value="recordList">
        	<%--超级管理员是不能修改删除的 --%>
            <tr class="TableDetail1 template">
                <td align="center">${loginName}&nbsp;</td>
                <td align="center">${name}&nbsp;</td>
                <td align="center">${phoneNumber}&nbsp;</td>
                <td align="center">${department.name}&nbsp;</td>
                <td align="center">&nbsp;
                	<s:iterator value="roles">${name}&nbsp;</s:iterator>
                </td>
                <td align="center">${jobTitle}&nbsp;</td>
                <td align="center">
	                <s:if test="%{status == 1}">正常</s:if>
	                <s:else>停用</s:else>
                </td>
                <td align="center">&nbsp;
                <s:if test="%{loginName != 'admin'}">
                	<s:a action="user_delete?id=%{id}" onClick="return delConfirm()">删除</s:a>&nbsp;
                    <s:a action="user_updateUI?id=%{id}">修改</s:a>&nbsp;
					<s:a action="user_initPassword?id=%{id}" onClick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</s:a>
				</td>
        		</s:if>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="user_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
</div>
<!--分页信息-->
<%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %> 

<!-- 说明 -->
<div class="Description">
	<font size="3" color="red">说明：</font><br><br />
	1、用户列表列出了用户的主要信息。<br/><br/>
	2、查看用户更多信息可在修改页面查看。<br/><br>
	3、用户除了拥有其他角色外，"普通教工"是基本角色。<br/><br/>
</div>

</body>
</html>
