<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>角色设置</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<script type="text/javascript">
		$(document).ready(function(){
			$("[name=name]").focus();
		});
		
		$(function(){
			$("#form").submit(function(){
				if($("[name=name]").val() == ""){
					alert("请输入字典名称");
					$("[name=name]").focus();
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 字典设置
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
	<!-- 将修改页面和添加页面合并，通过三目运算符判断id是否为空来判断action是添加还是修改 id为空时添加-->
    <s:form action="ZDbargainType_%{id == null ? 'add' : 'update'}" id="form">
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
        	<s:if test="%{id == null}">添加合同类型字典</s:if>
        	<s:else>修改合同类型字典</s:else>
        	</DIV>  
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                   <tr>
                        <td width="100px">名称</td>
                        <td><s:textfield name="name" cssClass="InputStyle"></s:textfield><font color="red"> *</font></td>
                    	<td><font color="red"><s:fielderror name="name"></s:fielderror> </font></td>
                    </tr>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
   </s:form>
</div>

<!-- 说明 -->
<div class="Description">
	<font size="3" color="red">说明：</font><br><br />
	1、字典名称不能为空。<br/><br/>
	2、字典名称不能重复。<br/><br/>
</div>

</body>
</html>

