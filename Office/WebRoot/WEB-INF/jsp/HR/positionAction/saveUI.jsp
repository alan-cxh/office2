<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>添加职位变更页面</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<script type="text/javascript">
		$(document).ready(function(){
	    	$("#department").focus();
	    });
		
		//开启验证
	    $(function() {
		    $("#form").validate({
		        messages:{
		        	departmentId:{required : "请选择部门"},
		            roleIds:{required : "请选择角色"},
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 职位变更设置
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
	<!-- 将修改页面和添加页面合并，通过三目运算符判断id是否为空来判断action是添加还是修改 id为空时添加-->
    <s:form action="position_add" id="form">
    	<s:hidden name="userId"></s:hidden>
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
        	 用户个人信息(不可修改)
        	</DIV>  
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">姓名</td>
                        <td><s:textfield name="name" cssClass="InputStyle" disabled="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100">性别</td>
                        <td><s:textfield name="gender" cssClass="InputStyle" disabled="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100">生日</td>
                        <td><s:textfield name="birthday" cssClass="InputStyle" disabled="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100">联系电话</td>
                        <td><s:textfield name="phoneNumber" cssClass="InputStyle" disabled="true"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100">现职位</td>
                        <td>
                        	<s:iterator value="roles">
                        		<s:label>${name}&nbsp;</s:label>
                        	</s:iterator>
                        </td>
                    </tr>
                    <tr>
                        <td width="100">现部门</td>
                        <td><s:textfield name="department.name" cssClass="InputStyle" disabled="true"></s:textfield></td>
                    </tr>
                </table>
            </div>
        </div>
        
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
        	 变更前的信息
        	</DIV>  
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">所属部门</td>
                        <td><!-- 动态显示要选择的上级部门 listKey="id"是提交时处理  ；list="#departmentList"是OGNL取值，listValue="name"是显示数据-->
                        	<s:select 
                        		name="departmentId" cssClass="SelectStyle required"
                        		list="#departmentList" listKey="id" listValue="name"
                        		headerKey="" headerValue="=======请选择所属部门=======" id="department">
                        	</s:select><font color="#A33639"> *职位变更前所属部门*</font>
                        </td>
                    </tr>
                    <tr>
                        <td width="100" valign="top">职位</td>
                        <td><s:select
								name="roleIds" multiple="true" size="10" cssClass="SelectStyle required"
                        		list="#roleList" listKey="id" listValue="name">
                            </s:select>
                            <font color="#A33639"> *按住Ctrl键可以多选或取消选择*</font>
                        </td>
                    </tr>
                    <tr>
                        <td width="100" valign="top">职位变更时间</td>
                        <td><s:textfield name="changeTime" cssClass="InputStyle Wdate" onclick="WdatePicker()"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100" valign="top">职位变更原因</td>
                        <td><s:textarea name="reason" cssClass="TextareaStyle"></s:textarea></td>
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
	1、用户个人信息指当前职工的现在的信息(不可修改)。<br/><br/>
	2、变更前的信息：请填写当前职工之前的部门、角色等信息。<br/>
</div>

</body>
</html>

