<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>人事合同列表</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 人事合同
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">

<!--按指定条件查询-->
	<s:form action="personBargain_list" method="post" id="pageForm">
		<div id="TableTail" style="margin-left: -50px"> 
			<div id="TableTail_inside">
				<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left" style="padding-bottom: 10px">
					<tr valign="bottom" style="padding-bottom: 10px">
						<td></td>
						<td>
							姓名：
							<s:textfield name="VuserName"></s:textfield>
							合同类型：
							<s:select name="Vtype"
	                        		list="#bargainTypeList" listKey="name" listValue="name"
	                        		headerKey="" headerValue="====请选择合同类型===="
	                        />
							<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG" align="ABSMIDDLE"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!--表头：显示基本信息-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="10%">职工姓名</td>
            	<td width=20%">合同名称</td>
            	<td width=10%">合同类型</td>
            	<td width="30%">合同描述</td>
                <td width="30%">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        <s:if test="count == 0">
        	<td align="center" colspan="5"><font color="red" size="4">没有人事合同相关记录</font></td>
        </s:if>
        <s:else>
        	<s:iterator value="recordList">
				<tr class="TableDetail1 template">
					<td align="center">${userName}&nbsp;</td>
					<td align="center">${title}&nbsp;</td>
					<td align="center">${type}&nbsp;</td>
					<td align="center">${description}&nbsp;</td>
					<td align="center">&nbsp;
						<s:a action="personBargain_delete?id=%{id}" onClick="return confirm('您确定要删除本条记录吗?')">删除</s:a>&nbsp;
						<s:a action="personBargain_updateUI?id=%{id}">修改</s:a>&nbsp;
						<s:a action="personBargain_showOnline?id=%{id}">在线预览</s:a>&nbsp;
						<s:a action="personBargain_download?id=%{id}">下载附件</s:a>
					</td>
				</tr>
			</s:iterator>
        </s:else>
        </tbody>
    </table>
    
    <!-- 其他功能超链接，也就是新建 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="personBargain_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png"/></s:a>
        </div>
    </div>
</div
<!--分页信息-->
<%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %> 
<!-- 说明 -->
<div class="Description">
	<font size="3" color="red">说明：</font><br><br />
	1、列表中只显示了人事合同的基本信息。<br/><br/>
	2、若要查看合同详细信息，请点击相关操作的"查看详细信息"。<br/><br/>
</div>
</body>
</html>