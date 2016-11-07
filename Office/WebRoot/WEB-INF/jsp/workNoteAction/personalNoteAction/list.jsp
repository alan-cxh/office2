<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>个人日志</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 个人日志管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
<!--按指定条件查询-->
	<s:form action="personalNote_list" method="post" id="pageForm">
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
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="30%">日志标题</td>
                <td width="20%">作者</td>
                <td width="20%">创建时间</td>
                <td>相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        <s:if test="count == 0">
        	<td align="center" colspan="5"><font color="red" size="4">没有个人日志相关记录</font></td>
        </s:if>
        <s:else>
        <s:iterator value="recordList">
            <tr class="TableDetail1 template">
                <td align="center">${title}&nbsp;</td>
                <td align="center">${author}&nbsp;</td>
                <td align="center">${time}&nbsp;</td>
                <td align="center">&nbsp;
	                <s:a action="personalNote_delete?id=%{id}" onClick="return delConfirm()">删除</s:a>&nbsp;
	                <s:a action="personalNote_updateUI?id=%{id}">修改</s:a>&nbsp;
               	    <s:a action="personalNote_showDetailUI?id=%{id}">查看详细内容</s:a>&nbsp;
                </td>
            </tr>
        </s:iterator>
        </s:else>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="personalNote_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
</div>

<!--分页信息-->
<%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %> 
<!-- 说明 -->
<div class="Description">
	<font size="3" color="red">说明：</font><br><br />
	1、个人日志只有自己可添加、删除，修改等操作。<br/><br/>
	2、"查看详细内容"可查看详细日志详细信息。<br/><br>
</div>

</body>
</html>
