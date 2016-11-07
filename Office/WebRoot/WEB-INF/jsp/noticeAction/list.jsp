<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>公告列表</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <script type="text/javascript">
    	function showDetail(tId) {
				tId = encodeURI(tId);
				var url = "notice_detailInfoUI.action?id=" + tId + "&t=" + new Date();
				window.showModalDialog(url, null,
						"dialogHeight:410px;dialogWidth:722px;resizable:yes");
		}
    </script>
</head>
<body>
 
<div id="Title_bar" data-role="page">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 公告管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">

<!--按指定条件查询-->
	<s:form action="notice_list" method="post" id="pageForm">
		<div id="TableTail" style="margin-left: -50px"> 
			<div id="TableTail_inside">
				<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left" style="padding-bottom: 10px">
					<tr valign="bottom" style="padding-bottom: 10px">
						<td></td>
						<td>
							标题：
							<s:textfield name="Vtitle"></s:textfield>
							
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
            	<td width="5%">编号</td>
            	<td width="40%">公告标题</td>
            	<td width="15%">发布人</td>
                <td width="25%">发布时间</td>
                <td width="15%">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        	<s:iterator value="recordList">
				<tr class="TableDetail1 template">
					<td align="center">GG_0${id}</td>
					<td align="center"><a href="javascript: showDetail('${id}')"><font color="#A33639" size="2" face="黑体">${title}</font></a>&nbsp;</td>
					<td align="center">${publishUser.name}&nbsp;</td>
					<td align="center">${publishDate}&nbsp;</td>
					<td align="center">&nbsp;
						<s:a action="notice_delete?id=%{id}" onClick="return confirm('您确定要删除本条记录吗?')">删除</s:a>&nbsp;
						<s:a action="notice_updateUI?id=%{id}">修改</s:a>&nbsp;
					</td>
				</tr>
			</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接，也就是新建 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="notice_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png"/></s:a>
        </div>
    </div>
</div>
<!--分页信息-->
<%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %> 
    
<!-- 说明 -->
<div class="Description">
	<font size="3" color="red">说明：</font><br><br />
	1、若需要查看公告详细信息，可点击"公告标题"查看对应公告的详细信息。<br/><br/>
</div>
</body>
</html>