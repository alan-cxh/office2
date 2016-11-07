<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>
	<title>我的日志阅读</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />

    <!-- 引入CKeditor的核心js文件 -->
	<script language="javascript" src="${pageContext.request.contextPath}/script/ckeditor/ckeditor.js" charset="utf-8"></script>
    <script type="text/javascript">
		
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 阅读我的日志
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
<s:form>
	<s:hidden name="id"></s:hidden>
	<div id="PageHead"></div>
	<center>
		<div class="ItemBlock_Title1" style="height: 30px">
			<div style="float:left;">
				<font class="MenuPoint"> &gt; </font>
				您正在阅读&nbsp;[${title}]&nbsp;......
			</div>
		</div>
		<div class="ItemBlockBorder">
			<table border="0" cellspacing="1" cellpadding="1" width="100%" id="InputArea">
				<tr>
					<td class="InputAreaBg" height="30" width="20px"><div class="InputTitle">标题</div></td>
					<td class="InputAreaBg" height="30" align="center">
						<div class="InputContent">
							<s:textfield name="title" class="InputStyle" cssStyle="width:60%;height:30px;text-align: center" disabled="true"/>
						</div>
					</td>
				</tr>
				
				<tr></tr>
				<tr height="240">
					<td class="InputAreaBg" valign="top"><div class="InputTitle">内容</div></td>
					<td class="InputAreaBg" valign="top">
						<div style="text-indent: 2em;margin-top: 10px;">
							${content}
						</div>
					</td>
				</tr>
				<%-- <tr height="40px">
					<td class="InputAreaBg" colspan="2" align="right" style="padding-right: 30px">
						<a href="publicNote_praise.action?noteId=${id}"><img src="${pageContext.request.contextPath}/style/images/myImages/zan.gif" width="20px" height="20px" title="赞"/>[${praiseCount}]</a>&nbsp;&nbsp;
						<a href="publicNote_demote.action?noteId=${id}"><img src="${pageContext.request.contextPath}/style/images/myImages/cai.gif" width="20px" height="20px" title="踩"/>[${demoteCount}]</a>&nbsp;&nbsp;
						<a href="publicNote_reprint.action?noteId=${id}" onClick="return confirm('您确定将该日志转载为自己的吗？')"><img src="${pageContext.request.contextPath}/style/images/myImages/zhuanzai.jpg" width="20px" height="20px" title="转载"/>[${reprintCount}]</a>
					</td>
				</tr> --%>
			</table>
		</div>
	</center>
	
</s:form>
<div style="background-color: #F3F9FD;padding-top: 10px" align="center">
	<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/blue/images/button/goBack.png" align="center"/></a>
</div>
</div>

</body>
</html>
