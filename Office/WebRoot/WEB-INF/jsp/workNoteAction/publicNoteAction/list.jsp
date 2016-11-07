<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
</head>
<body>
<div class="ForumPageTableBorder" style="margin-top: 10px; width: 100%">
	<table style="padding-left: 20px">
		<tr>
			<td width="10%" align="center">
			<s:a action="publicNote_addUI">
			<img src="${pageContext.request.contextPath}/style/images/myImages/note1.png" width="35px" height="35px"/>
			</s:a>
			</td>
			<td width="10%" align="center">
			<s:a action="publicNote_myNoteList">
			<img src="${pageContext.request.contextPath}/style/images/myImages/note2.png" width="30px" height="30px"/>
			</s:a>
			</td>
			<td></td>
		</tr>
		<tr>
			<td width="10%" align="center">
			<s:a action="publicNote_addUI">写日志</s:a>
			</td>
			<td width="10%" align="center">
			<s:a action="publicNote_myNoteList">我的公开日志</s:a>
			</td>
			<td></td>
		</tr>
	</table>
</div>

<div id="MainArea">

<!--按指定条件查询-->
	<s:form action="publicNote_list" method="post" id="pageForm">
		<div id="TableTail" style="margin-left: -50px"> 
			<div id="TableTail_inside">
				<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left" style="padding-bottom: 10px">
					<tr valign="bottom" style="padding-bottom: 10px">
						<td></td>
						<td>
							标题：
							<s:textfield name="Vtitle"></s:textfield>
							类型：
							<s:select name="Vtype" list="#{null:'==日志类型==','转载':'转载','原创':'原创'}"/>
							
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
			<!--表头-->
			<tr align="center">
				<td class="ForumPageTableTitle" align="center" width="55px"></td>
				<td class="ForumPageTableTitle" align="center" style="font-size: 15px;">标题</td>
				<td class="ForumPageTableTitle" align="center" style="font-size: 15px;">作者</td>
				<td class="ForumPageTableTitle" align="center" style="font-size: 15px;">类型</td>
				<td class="ForumPageTableTitle" align="center" style="font-size: 15px;">来源</td>
				<td class="ForumPageTableTitle" align="center" style="font-size: 15px;">发表日期</td>
				<td class="ForumPageTableTitle" align="center" style="font-size: 15px;">赞</td>
				<td class="ForumPageTableTitle" align="center" style="font-size: 15px;">踩</td>
				<td class="ForumPageTableTitle" align="center" style="font-size: 15px;">转载</td>
				<td class="ForumPageTableTitle" align="center" style="font-size: 15px;">相关操作</td>
			</tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        <s:if test="count == 0">
        	<td align="center" colspan="10" class="ForumPageTableDataLine"><font color="red" size="4">没有公开日志相关记录</font></td>
        </s:if>
        <s:else>
        <s:iterator value="recordList">
            <tr class="TableDetail1 template">
                <td align="center" class="ForumPageTableDataLine" width="55px">
               	<s:a action="publicNote_readNoteUI?id=%{id}">
					<img src="${pageContext.request.contextPath}/style/images/myImages/noteList.jpg" width="55px" height="60px"/>
				</s:a>
                </td>
                
                <td align="center" class="ForumPageTableDataLine">
                	<s:a action="publicNote_readNoteUI?id=%{id}">${title}&nbsp;</s:a>
                </td>
                <td align="center" class="ForumPageTableDataLine">${author}&nbsp;</td>
                <s:if test="type == 0">
                <td align="center" class="ForumPageTableDataLine">原创&nbsp;</td>
                </s:if>
                <s:else>
                <td align="center" class="ForumPageTableDataLine">转载&nbsp;</td>
                </s:else>
                <td align="center" class="ForumPageTableDataLine">${oldAuthor}&nbsp;</td>
                <td align="center" class="ForumPageTableDataLine">${time}&nbsp;</td>
                <td align="center" class="ForumPageTableDataLine">${praiseCount}&nbsp;</td>
                <td align="center" class="ForumPageTableDataLine">${demoteCount}&nbsp;</td>
                <td align="center" class="ForumPageTableDataLine">${reprintCount}&nbsp;</td>
                <td align="center">&nbsp;
	                <s:a action="publicNote_readNoteUI?id=%{id}">阅读日志</s:a>&nbsp;
                </td>
            </tr>
        </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>

<!-- 其他功能超链接，也就是新建 -->
<div style="height: 2px;background-color: #3E88B9;">
</div>
<!--分页信息-->
<%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %> 

<!-- 说明 -->
<div class="Description">
	<font size="3" color="red">说明：</font><br><br />
	1、"写日志"是公共的日志，都可以查看。<br/><br/>
	2、"我的公开日志"可以对自己发表的公开日志进行管理。<br/><br>
	3、日志根据时间的最近发表时间排序<br/><br>
</div>

</body>
</html>
