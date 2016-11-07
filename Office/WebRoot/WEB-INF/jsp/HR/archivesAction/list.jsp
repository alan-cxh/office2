<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>档案列表</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 档案管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">

<!--按指定条件查询-->
	<s:form action="archives_list" method="post" id="pageForm">
		<div id="TableTail" style="margin-left: -50px"> 
			<div id="TableTail_inside">
				<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left" style="padding-bottom: 10px">
					<tr valign="bottom" style="padding-bottom: 10px">
						<td></td>
						<td>
							姓名：
							<s:textfield name="VuserName"></s:textfield>
							
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
            <tr align="center" valign="middle" id="TableTitle">
            	<td width="10%">职工姓名</td>
            	<td width="20%">档案标题</td>
            	<td width="30%">档案描述</td>
                <td width="40%">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        <s:if test="count == 0">
        	<td align="center" colspan="5"><font color="red" size="4">没有档案管理的相关记录</font></td>
        </s:if>
        <s:else>
        	<s:iterator value="recordList">
				<tr class="TableDetail1 template">
					<td align="center">${user.name}</td>
					<td align="center">${title}&nbsp;</td>
					<td align="center">${description}&nbsp;</td>
					<td align="center">&nbsp;
						<s:a action="archives_delete?id=%{id}" onClick="return confirm('您确定要删除本条记录吗?')">删除</s:a>&nbsp;
						<s:a action="archives_updateUI?id=%{id}">修改</s:a>&nbsp;
						<s:a action="archives_showOnline?id=%{id}">在线预览</s:a>&nbsp;
						<s:a action="archives_download?id=%{id}">下载档案</s:a>
					</td>
				</tr>
			</s:iterator>
        </s:else>
        </tbody>
    </table>
    
    <!-- 其他功能超链接，也就是新建 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="archives_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png"/></s:a>
        </div>
    </div>
</div>
    
<!-- 说明 -->

<!--分页信息-->
<%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %> 
<div class="Description">
	<font size="3" color="red">说明：</font><br><br />
	1、点击查看附件可查看附件并下载。<br/><br/>
	2、下载的的档案文件的默认文件名为："登录名_教工姓名_档案"，如: "0701_陶然_档案"。<br/><br/>
	3、删除档案记录将同时删除保存在服务器上的档案。<br/>
</div>
</body>
</html>