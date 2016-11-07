<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>
	<title>查看主题:${topic.title}</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	
	<!-- 引入FCKeditor的核心js文件 -->
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 查看主题
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--内容显示-->	
<div id="MainArea">
	<div id="PageHead"></div>
	<center>
		<s:hidden name="forumId"></s:hidden>
		
		<div class="ItemBlock_Title1" style="width: 98%">
			<font class="MenuPoint"> &gt; </font>
			<s:a action="forum_list">论坛</s:a>
			<font class="MenuPoint"> &gt; </font>
			<s:a action="forum_show?id=%{#topic.forum.id}">${topic.forum.name}</s:a>
			<font class="MenuPoint"> &gt;&gt; </font>
			帖子阅读
			<span style="margin-left:30px;">
			<s:a action="topic_addUI?forumId=%{#topic.forum.id}">
				<img src="${pageContext.request.contextPath}/style/blue/images/button/publishNewTopic.png"/>
			</s:a>
			</span>
		</div>
		
		<div class="ForumPageTableBorder dataContainer">
		
			<!--显示主题标题等-->
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
				<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle" height="30px"><font size="3" face="黑体" color="green">本帖主题：${topic.title}</font></td>
					<td class="ForumPageTableTitle" align="right" style="padding-right:12px;">
						<s:a class="detail" action="reply_addUI?topicId=%{#topic.id}"><img border="0" src="${pageContext.request.contextPath}/style/images/reply.gif" />回复</s:a>
						
						<s:a action="topic_setTop?id=%{id}" onClick="return confirm('您确定要把本主题设为置顶吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/topicType_2.gif" />置顶</s:a>
						<s:a action="topic_setBest?id=%{id}" onClick="return confirm('您确定要把本主题设为精华吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/topicType_1.gif" />精华</s:a>
						<s:a action="topic_setNormal?id=%{id}" onClick="return confirm('您确定要把本主题设为普通吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/topicType_0.gif" />普通</s:a>
					</td>
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="4"></td></tr>
			</table>

			<!-- ~~~~~~~~~~~~~~~ 显示主帖 (主贴只在第一页显示)~~~~~~~~~~~~~~~ -->
			<s:if test="currentPage == 1">
			<div class="ListArea">
				<table border="0" cellpadding="0" cellspacing="1" width="100%">
					<tr>
						<td rowspan="3" width="130" class="PhotoArea" align="center" valign="top">
							<!--作者头像:默认头像-->
							<div class="AuthorPhoto">
								<img border="0" width="110" height="110" src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif"/>
							</div>
							<!--作者名称-->
							<div class="AuthorName">${topic.author.name}</div>
						</td>
						<td align="center">
							<ul class="TopicFunc">
								<!--操作列表-->
								<li class="TopicFuncLi">
									<!-- 论坛里面不应该对主贴有修改，当有评论后，修改主贴后就主贴没意义了 -->
									<%--<s:a class="detail" action="topic_updateUI?id=%{id}"><img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif" />编辑</s:a> --%>
									<s:a class="detail" action="topic_delete?id=%{id}" onClick="return confirm('删除主贴将删除所有回复,您确定要删除吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/delete.gif"/>删除</s:a>
								</li>
								<!-- 文章标题 -->
								<li class="TopicSubject">
									<%-- <img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face1.gif"/> --%>
									${topic.title}
								</li>
							</ul>
						</td>
					</tr>
					<tr><!-- 文章内容 -->
						<td valign="top" align="center">
							<div class="Content" style="line-height: 1.5;text-indent: 2em">${topic.content}</div>
						</td>
					</tr>
					<tr><!--显示楼层等信息-->
						<td class="Footer" height="28" align="center" valign="bottom">
							<ul style="margin: 0px; width: 98%;">
								<li style="float: left; line-height:18px;"><font color=#C30000 size="3" face="黑体">
									[楼主]
									${topic.postTime}</font>
								</li>
							</ul>
						</td>
					</tr>
				</table>
			</div>
			</s:if>
			<!-- ~~~~~~~~~~~~~~~ 显示主帖结束 ~~~~~~~~~~~~~~~ -->
			
			
			<div style="height: 5px;background-color: black;"></div>
			<!-- ~~~~~~~~~~~~~~~ 显示回复列表 ~~~~~~~~~~~~~~~ -->
			<s:iterator value="recordList" status="status">
			<div class="ListArea template">
				<table border="0" cellpadding="0" cellspacing="1" width="100%">
					<tr>
						<td rowspan="3" width="130" class="PhotoArea" align="center" valign="top">
							<!--作者头像： 默认头像-->
							<div class="AuthorPhoto">
								<img border="0" width="110" height="110" src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif"/>
							</div>
							<!--作者名称-->
							<div class="AuthorName">${author.name}</div>
						</td>
						<td align="center">
							<ul class="TopicFunc">
								<!--操作列表-->
								<li class="TopicFuncLi">
									<%-- 发表的回复也不应该有修改，不然也没有意义，只能有管理员删除 --%>
									<%-- <s:a action="" class="detail"><img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif"/>编辑</s:a> --%>
									<s:a action="reply_delete?id=%{id}" class="detail" onClick="return confirm('您确定要删除该回复吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/delete.gif"/>删除</s:a>
								</li>
								<!-- 文章表情与标题 -->
								<li class="TopicSubject">
									<%-- <img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/${reply.faceIcon}"/> --%>
									${title}
								</li>
							</ul>
						</td>
					</tr>
					<tr style="line-height: 1.5;text-indent: 2em"><!-- 文章内容 -->
						<td valign="top" align="center">
							<div class="Content">
								${content}
							</div>
						</td>
					</tr>
					<tr><!--显示楼层等信息-->
						<td class="Footer" height="28" align="center" valign="bottom">
							<ul style="margin: 0px; width: 98%;">
								<li style="float: left; line-height:18px;">
									<font color=#C30000 size="3" face="黑体">
										[${(currentPage - 1) * pageSize + status.count}楼]
									</font>
									<font color=black size="2" face="黑体">
										${postTime}
									</font>
								</li>
								<%-- <li style="float: right;"><a href="javascript:scroll(0,0)">
									<img border="0" src="${pageContext.request.contextPath}/style/images/top.gif" /></a>
								</li> --%>
							</ul>
						</td>
					</tr>
				</table>
			</div>
			</s:iterator>
			<!-- ~~~~~~~~~~~~~~~ 显示回复列表结束 ~~~~~~~~~~~~~~~ -->
		</div>

		<!--分页信息-->
		<%-- <s:form action="topicAction_show?id=%{id}"></s:form> --%>
	    <%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %> 
		<script type="text/javascript">
			function gotoPage( pageNum ){
				window.location.href = "${pageContext.request.contextPath}/topic_show.action?id=${id}&pageNum=" + pageNum;
			
			}
		</script>
			
		<!-- 快速回复 -->
		<div class="ForumPageTableBorder" style="margin-top: 25px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
					<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle"><font size="5">快速回复</font></td>
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine">
					<td colspan="3"></td>
				</tr>
			</table>
		</div>
	</center>
			
			
	<!--快速回复-->
	<center>
	<div class="QuictReply" style="width: 80%;margin-top: 30px;padding-top: 20px;">
	<s:form action="reply_add?topicId=%{#topic.id}" onsubmit="return isValid_addReplyUI(this);">
		<div style="padding-left: 3px;">
			<table border="0" cellspacing="1" width="100%" cellpadding="5" class="TableStyle">
				<tr height="30" class="Tint">
					<td width="50px" class="Deep"><b>标题</b></td>
					<td class="no_color_bg">
						<s:textfield name="title" cssClass="InputStyle" readonly="true" value="回复：%{#topic.title}" cssStyle="width:100%"/>
					</td>
				</tr>
				
				<tr class="Tint" height="200">
					<td valign="top" rowspan="2" class="Deep"><b>内容</b></td>
					<td valign="top" class="no_color_bg">
						<s:textarea name="content" cssStyle="width: 100%; height: 500px"></s:textarea>
						<script type="text/javascript">CKEDITOR.replace('content',{
								customConfig : 'myConfig.js',//加载的配置文件：可自定义，否则默认
								toolbar : 'Normal',	//自定义的工具条
							});
						</script>
					</td>
				</tr>
				<tr height="30" class="Tint">
					<td class="no_color_bg" colspan="2" align="center">
						<input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" style="margin-right:15px;"/>
						<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/blue/images/button/goBack.png"/></a>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
	</div>
</div>
</center>

<div class="Description">
	<font size="3" color="red">说明：</font><br><br/>
	1、主帖只在第一页显示。<br/><br/>
	2、只有是管理员才可以进行“删除”、“精华”、“置顶”、“普通”操作。<br/><br/>
	3、删除主帖，就会删除所有的跟帖（回复）。<br/>
</div>

</body>
</html>
