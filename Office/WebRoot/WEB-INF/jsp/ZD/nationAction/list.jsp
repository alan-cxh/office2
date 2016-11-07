<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>民族字典列表</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <script type="text/javascript"></script>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 民族字典管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">

<!--按指定条件查询-->
	<s:form action="ZDnation_list" method="post" id="pageForm">
		<div id="TableTail" style="margin-left: -50px"> 
			<div id="TableTail_inside">
				<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left" style="padding-bottom: 10px">
					<tr valign="bottom" style="padding-bottom: 10px">
						<td></td>
						<td>
							民族：
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
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="25%">编号</td>
                <td width="40%">名称</td>
                <td width="25%">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        	<s:iterator value="recordList">
				<tr class="TableDetail1 template">
					<td align="center">${id}&nbsp;</td>
					<td align="center">${name}&nbsp;</td>
					<td align="center">
						<s:a action="ZDnation_delete?id=%{id}" onClick="return delConfirm()">删除</s:a>&nbsp;
						<s:a action="ZDnation_updateUI?id=%{id}">修改</s:a>&nbsp;
					</td>
				</tr>
			</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接，也就是新建 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="ZDnation_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png"/></s:a>
        </div>
    </div>
</div>
  
<!--分页信息-->
<%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %>   
<!-- 说明 -->
<div class="Description">
	<font size="3" color="red">说明：</font><br><br/>
</div>

</body>
</html>
