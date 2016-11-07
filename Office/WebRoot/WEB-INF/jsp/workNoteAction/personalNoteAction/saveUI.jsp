<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>
	<title>发表新主题</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />

    <!-- 引入CKeditor的核心js文件 -->
	<script language="javascript" src="${pageContext.request.contextPath}/script/ckeditor/ckeditor.js" charset="utf-8"></script>
    <script type="text/javascript">
		$(document).ready(function(){
			if($("#id").val() == ""){
				$("[name=title]").focus();
			}
		});
		
		$(function(){
			$("#form").submit(function(){
				var title = $("[name=title]").val();//根据name取值
				if(title == ""){
					alert("请输入标题！");
					$("[name=title]").focus();
					return false;
				}else{
					return true;
				}
			});
		});
		
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 设置日志
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
<s:form action="personalNote_%{id == null ? 'add' : 'update'}" method="post" id="form">
	<s:hidden name="id" id="id"></s:hidden>
	<div id="PageHead"></div>
	<center>
		<div class="ItemBlock_Title1" style="height: 30px">
			<div style="float:left;">
				<font class="MenuPoint"> &gt; </font>
				<s:if test="%{id == null}">
					添加日志 
				</s:if>
				<s:else>
					修改日志
				</s:else>
			</div>
		</div>
		<div class="ItemBlockBorder">
			<table border="0" cellspacing="1" cellpadding="1" width="100%" id="InputArea">
				<tr>
					<td class="InputAreaBg" height="30"><div class="InputTitle">标题</div></td>
					<td class="InputAreaBg" height="30" align="center">
						<div class="InputContent">
							<s:textfield name="title" class="InputStyle" cssStyle="width:60%;height:30px" />
						</div>
					</td>
				</tr>
				
				<tr></tr>
				<tr height="240">
					<td class="InputAreaBg" valign="top"><div class="InputTitle">内容</div></td>
					<td class="InputAreaBg">
						<div class="InputContent">
							<s:textarea name="content" cssStyle="width:100%;height:250px" placeholder="学习上、工作上、生活中有什么心得，记得写下来..."></s:textarea>
							<script type="text/javascript">CKEDITOR.replace('content',{
								customConfig : 'myConfig.js',//加载的配置文件：可自定义，否则默认
								toolbar : 'Normal',	//自定义的工具条
							});
							</script>
						</div>
					</td>
				</tr>
				<tr height="30">
					<td class="InputAreaBg" colspan="2" align="center">
						<input type="image" src="${pageContext.request.contextPath}/style/images/save.png" style="margin-right:15px;"/>
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
	1、标题不能为空，长度不能超过100个中文符<br/><br/>
</div>

</body>
</html>
