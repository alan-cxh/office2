<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>
	<title>发表新主题</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />

	<!-- 引入CKeditor的核心js文件 -->
	<script language="javascript" src="${pageContext.request.contextPath}/script/ckeditor/ckeditor.js" charset="utf-8"></script>
    <script type="text/javascript"></script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 发表新主题
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
<s:form action="topic_add" method="post" onsubmit="return isValid_addTopicUI(this);">
	<s:hidden name="forumId"></s:hidden>
	<div id="PageHead"></div>
	<center>
		<div class="ItemBlock_Title1" style="height: 30px">
			<div style="float:left;">
				<font class="MenuPoint"> &gt; </font>
				<s:a action="forum_list">论坛</s:a>
				<font class="MenuPoint"> &gt; </font>
				<s:a action="forum_show?id=%{#forum.id}">${forum.name}</s:a>
				<font class="MenuPoint"> &gt;&gt; </font>
				发表新主题 
			</div>
		</div>
		<div class="ItemBlockBorder">
			<table border="0" cellspacing="1" cellpadding="1" width="100%" id="InputArea">
				<tr>
					<td class="InputAreaBg" height="30"><div class="InputTitle">标题</div></td>
					<td class="InputAreaBg" height="30">
						<div class="InputContent">
							<s:textfield name="title" class="InputStyle" cssStyle="width:100%"/>
						</div>
					</td>
				</tr>
				
				<tr></tr>
				<tr height="240">
					<td class="InputAreaBg" valign="top"><div class="InputTitle">内容</div></td>
					<td class="InputAreaBg">
						<div class="InputContent">
							<s:textarea name="content" cssStyle="width:100%;height:250px"></s:textarea>
							<script type="text/javascript">CKEDITOR.replace('content',{
								customConfig : 'myConfig.js', //加载的配置文件：可自定义，否则默认
								toolbar : 'Normal',	//自定义的工具条
							});
							</script>
							<script type="text/javascript">CKEDITOR.replace('content');</script>
						</div>
					</td>
				</tr>
				<tr height="30">
					<td class="InputAreaBg" colspan="2" align="center">
						<input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" style="margin-right:15px;"/>
						<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/blue/images/button/goBack.png"/></a>
					</td>
				</tr>
			</table>
		</div>
	</center>
</s:form>
</div>

<div class="Description">
<font size="3" color="red">说明：</font><br><br/>
	1、主题不能为空，长度不能超过100个中文符<br/><br/>
</div>

</body>
</html>
