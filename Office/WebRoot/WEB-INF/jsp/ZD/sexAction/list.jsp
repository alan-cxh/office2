<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>性别字典列表</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <script type="text/javascript"></script>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 性别字典管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!--表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="25%">编号</td>
                <td width="40%">名称</td>
                <td width="25%">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        	<s:iterator value="#sexList">
				<tr class="TableDetail1 template">
					<td align="center">${id}&nbsp;</td>
					<td align="center">${name}&nbsp;</td>
					<td align="center">
						<s:a action="ZDsex_delete?id=%{id}" onClick="return delConfirm()">删除</s:a>&nbsp;
						<s:a action="ZDsex_updateUI?id=%{id}">修改</s:a>&nbsp;
					</td>
				</tr>
			</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接，也就是新建 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="ZDsex_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png"/></s:a>
        </div>
    </div>
</div>
    
<!-- 说明 -->
<div class="Description">
	<font size="3" color="red">说明：</font><br><br/>
	1、请添加基本的性别字典：男、女、未知，在添加用户时，没有填写性别的时候默认为"未知"<br/><br/>
</div>

</body>
</html>
