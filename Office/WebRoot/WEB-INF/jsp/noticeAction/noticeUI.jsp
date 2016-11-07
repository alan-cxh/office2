<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>公告栏</title>
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
 
<div id="Title_bar" style="background-color: #1E6BA1;padding-top: 10px" align="center">
	<font size="5" color="white" face="黑体">系统公告</font>
</div>

<div id="MainArea">
<!--按指定条件查询-->
	<s:form action="notice_noticeUI" method="post" id="pageForm">
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
            	<td width="40%">公告标题</td>
            	<td width="30%">发布人</td>
                <td>发布时间</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        	<s:iterator value="recordList">
				<tr class="TableDetail1 template">
					<!-- 点击标题弹出对话框显示详细信息 -->
					<td align="center"><a href="javascript: showDetail('${id}')"><font color="#A33639" size="2" face="黑体">${title}</font></a>&nbsp;</td>
					<td align="center">${publishUser.name}&nbsp;</td>
					<td align="center">${publishDate}&nbsp;</td>
				</tr>
			</s:iterator>
        </tbody>
    </table>

    <!-- 其他功能超链接，也就是新建 -->
    <div style="height: 2px;background-color: #3E88B9;">
    </div>
</div>

<!--分页信息-->
<%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %> 
    
<!-- 说明 -->
<div class="Description">
	<font size="3" color="red">说明：</font><br><br />
	1、若需要查看公告详细信息，请点击"公告标题"查看对应公告的详细信息。<br/><br/>
</div>

</body>
</html>