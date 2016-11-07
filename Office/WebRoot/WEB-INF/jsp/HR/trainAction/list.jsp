<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>培训记录列表</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <script type="text/javascript">
    	function showDetail(tId) {
				tId = encodeURI(tId);
				var url = "train_detailInfoUI.action?id=" + tId + "&time=" + new Date();
				window.showModalDialog(url, null,
						"dialogHeight:450px;dialogWidth:640px;resizable:yes");
		}
    </script>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 培训记录
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">

<!--按指定条件查询-->
	<s:form action="train_list" method="post" id="pageForm">
		<div id="TableTail" style="margin-left: -50px"> 
			<div id="TableTail_inside">
				<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left" style="padding-bottom: 10px">
					<tr valign="bottom" style="padding-bottom: 10px">
						<td></td>
						<td>
							培训人员：
							<s:textfield name="Vname"></s:textfield>
							培训机构：
							<s:textfield name="Vdepartment"></s:textfield>
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
            	<td width="10%">培训人员</td>
            	<td width="20%">开始时间</td>
            	<td width="20%">培训机构</td>
                <td width="20%">结束时间</td>
                <td width="30%">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        <s:if test="count == 0">
        	<td align="center" colspan="5"><font color="red" size="4">没有职工培训的相关记录</font></td>
        </s:if>
        <s:else>
        	<s:iterator value="recordList">
				<tr class="TableDetail1 template">
					<td align="center">${userName}&nbsp;</td>
					<td align="center">${startTime}&nbsp;</td>
					<td align="center">${department}&nbsp;</td>
					<td align="center">${endTime}&nbsp;</td>
					<td align="center">&nbsp;
						<s:a action="train_delete?id=%{id}" onClick="return confirm('您确定要删除本条记录吗?')">删除</s:a>&nbsp;
						<s:a action="train_updateUI?id=%{id}">修改</s:a>&nbsp;
						<a href="javascript: showDetail('${id}')">查看详细信息</a>
					</td>
				</tr>
			</s:iterator>
        </s:else>
        </tbody>
    </table>
    
    <!-- 其他功能超链接，也就是新建 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="train_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png"/></s:a>
        </div>
    </div>
</div>
<!--分页信息-->
<%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %>   
<!-- 说明 -->
<div class="Description">
	<font size="3" color="red">说明：</font><br><br />
	1、列表中只显示了培训人员的培训基本信息。<br/><br/>
	2、若要查看培训详细信息，请点击相关操作的"查看详细信息"。<br/><br/>
</div>
</body>
</html>