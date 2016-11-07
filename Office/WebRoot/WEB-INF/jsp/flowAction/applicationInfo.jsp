<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <title>申请信息</title>
  </head>
  
<body>
<!-- 标题显示 --> 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 申请信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
	<div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
		<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 申请人信息查看 </DIV>
	</div>
	<!-- 申请人信息显示 -->
	<!-- 表单内容显示 -->
    <div class="ItemBlockBorder" style="margin-left: 20px">
		<div class="ItemBlock">
            <table cellpadding="0" cellspacing="0" class="mainForm">
            
                <tr><td>申请标题</td>
                    <td><s:label name="title"></s:label></td>
                </tr>
            	<tr><td>申请人</td>
                    <td><s:textfield name="applicant.name" cssClass="InputStyle" disabled="true"/></td>
                </tr>
                <tr><td>申请模板</td>
                    <td><s:textfield name="template.name" cssClass="InputStyle" disabled="true"/></td>
                <tr><td>申请时间</td>
                    <td><s:textfield name="applyTime" cssClass="InputStyle Wdate" disabled="true"/></td>
                </tr>
				<tr><td>当前状态</td>
                    <td><s:textfield name="status" cssClass="InputStyle" disabled="true"/></td>
                </tr>
            </table>
        </div>
    </div>
	
	<div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
		<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 申请文档 </DIV>
	</div>
	
	<div class="ItemBlockBorder" style="margin-left: 20px;">
		<div class="ItemBlock">
			<table cellpadding="0" cellspacing="0" class="mainForm" align="center">
				<tr>
					<td><a href="application_showOnline.action?id=${id}" style="text-decoration: underline">[在线浏览申请的文档]</a></td>
				</tr>
				<tr>
					<td><a href="application_download.action?id=${id}" style="text-decoration: underline">[点击下载申请的文档]</a></td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- 表单操作 -->
    <div id="InputDetailBar">
        <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
    </div>
</div>

<div class="Description">
	<font size="3" color="red">说明：</font><br><br/>
	1、本页面的信息是申请人的相关信息<br /><br />
	2、[点击下载申请的文档] 是申请人填写申请后随流程一起流动的申请文档<br />
</div>

</body>
</html>
