<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>培训记录管理</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<script type="text/javascript">
		$(document).ready(function(){
    		$("#userId").focus();
    	});
    	
    	//开启验证
	    $(function() {
		    $("#form").validate({
		        messages:{
		        	userId:{required : "请选择教工"},
		        	department:{required : "请选择培训机构"},
		        	startTime:{required : "请选择培开始时间"},
		        	endTime:{required : "请选择培训结束时间"},
		        	content:{required : "请输入培训内容"},
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 培训记录设置
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
	<!-- 将修改页面和添加页面合并，通过三目运算符判断id是否为空来判断action是添加还是修改 id为空时添加-->
    <s:form action="train_%{id == null ? 'add' : 'update'}" id="form">
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
        	 <s:if test="%{id == null}">添加培训记录</s:if>
        	 <s:else>修改培训记录信息</s:else>
        	</DIV>  
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">职工姓名</td>
                        <s:if test="%{id == null}"><!-- 添加页面 -->
                        <td>
                        <!-- 动态显示要选择的上级部门 listKey="id"是提交时处理  ；list="#departmentList"是OGNL取值，listValue="name"是显示数据-->
                        	<s:select 
                        		name="userId" cssClass="SelectStyle required"
                        		list="#userList" listKey="id" listValue="name"
                        		headerKey="" headerValue="==请选择职工==" id="userId">
                        	</s:select><font color="red"> *</font>
                        </td>
                        </s:if>
                        <s:else>
                        	<td><s:textfield name="userName" cssClass="InputStyle" disabled="true"></s:textfield></td>
                        </s:else>
                    </tr>
                    <tr>
                        <td width="100">开始时间</td>
                        <td><s:textfield name="startTime" cssClass="InputStyle Wdate required" onclick="WdatePicker()"></s:textfield><font color="red"> *</font></td>
                    </tr>
                    <tr>
                        <td width="100" valign="top">培训内容</td>
                        <td><s:textarea name="content" cssClass="TextareaStyle required"></s:textarea><font color="red"> *</font></td>
                    </tr>
                    <tr>
                        <td width="100">培训机构</td>
                        <td><s:textfield name="department" cssClass="InputStyle required"></s:textfield><font color="red"> *</font></td>
                    </tr>
                    <tr>
                        <td width="100">培训地点</td>
                        <td><s:textfield name="address" cssClass="InputStyle"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100">结束时间</td>
                        <td><s:textfield name="endTime" cssClass="InputStyle Wdate required" onclick="WdatePicker()"></s:textfield><font color="red"> *</font></td>
                    </tr>
                    <tr>
                        <td width="100" valign="top">培训结果</td>
                        <td><s:textarea name="result" cssClass="TextareaStyle"></s:textarea></td>
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
	<font size="3" color="red">说明：</font><br><br/>
	1、培训人员的姓名不能为空。<br/><br/>
	2、请填写相关属性的正确格式。<br/>
</div>

</body>
</html>

