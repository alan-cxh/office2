<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>工作日志</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	<script type="text/javascript">
	</script>
</head>
<body>
<div id="Title_bar">
	<div id="Title_bar_Head">
		<div id="Title_Head"></div>
		<div id="Title">
			<!--页面标题-->
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 工作日志 </div>
		<div id="Title_End"></div>
	</div>
</div>
<div id="MainArea">
	<center>
		<div class="ForumPageTableBorder" style="margin-top: 5px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<!--表头-->
				<tr align="center" align="center">
					<td class="ForumPageTableTitleLeft" align="center"></td>
					<td colspan="2" class="ForumPageTableTitleLeft" align="center" style="font-size: 20px;">工作日志类型</td>
					<td colspan="3" class="ForumPageTableTitle" align="center" style="font-size: 20px;">功能描述</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="6"></td></tr>
			
				<!--版面列表：-->
				<tbody class="dataContainer" style="">
					<tr height="78" align="center" class="template">
						<td width="3"></td>
						<td width="75" class="ForumPageTableDataLine">
						<s:a action="personalNote_list">
							<img src="${pageContext.request.contextPath}/style/images/myImages/myNote.jpg" width="70px" height="70px"/>
						</s:a>
						</td>
						<td class="ForumPageTableDataLine">
							<s:a action="personalNote_list"><font size="4">个人日志</font></s:a>
						</td>
						<td class="ForumPageTableDataLine" style="font-size: 16px">个人日志是自己创建、修改删除，个人拥有，其他用户没法看到，只有自己可见</td>
						<td width="3"></td>
					</tr>
				</tbody>
				<!-- 版面列表结束 -->
								
				<!--版面列表：公共通讯录-->
				<tbody class="dataContainer" style="">
					<tr height="78" align="center" class="template">
						<td width="3"></td>
						<td width="75" class="ForumPageTableDataLine">
						<s:a action="publicNote_list">
							<img src="${pageContext.request.contextPath}/style/images/myImages/publicNote.jpg" width="70px" height="60px" />
						</s:a>
						</td>
						<td class="ForumPageTableDataLine">
							<s:a action="publicNote_list"><font size="4">公开日志</font></s:a>
						</td>
						<td class="ForumPageTableDataLine" style="font-size: 16px">公开日志在OA内部中全部教工都能看见，并可以转载，点赞，踩等操作</td>
						<td width="3"></td>
					</tr>
				</tbody>
				<!-- 版面列表结束 -->
				<tr height="3"><td colspan="9"></td></tr>
			</table>
		</div>
	</center>
</div>
</body>
</html>
