<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>公告管理</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<script type="text/javascript">
		$(function(){
			$("#form").submit(function(){
				if($("[name=title]").val() == ""){
					alert("请输入公告标题");
					$("[name=title]").focus();
					return false;
				}
				if($("[name=content]").val() == ""){
					alert("请输入公告内容");
					$("[name=content]").focus();
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 公告设置
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
	<!-- 将修改页面和添加页面合并，通过三目运算符判断id是否为空来判断action是添加还是修改 id为空时添加-->
    <s:form action="notice_%{id == null ? 'add' : 'update'}" id="form">
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
        	 <s:if test="%{id == null}">发布新公告</s:if>
        	 <s:else>修改公告</s:else>
        	</DIV>  
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">公告标题</td>
                        <td><s:textfield name="title" cssClass="InputStyle"></s:textfield> &nbsp;&nbsp;<font color="#A33639">*公告标题不能为空*</font></td>
                    </tr>
                    
                    <tr>
                        <td valign="top">公告内容</td>
                        <td><s:textarea name="content" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/publish.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
   </s:form>
</div>

<!-- 说明 -->
<div class="Description">
	<font size="3" color="red">说明：</font><br><br />
	1、公告标题和公告内容均不能为空。<br/><br/>
	2、公告标题最大长度为25个中文字符，公告内容最大长度为500个汉字。<br />
</div>

</body>
</html>

