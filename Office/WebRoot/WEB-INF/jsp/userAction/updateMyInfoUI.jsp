<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>
	<title>维护个人信息</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <script type="text/javascript">
    	// 联系电话(手机/电话皆可)验证   
	    jQuery.validator.addMethod("isTel", function(value,element) {   
	        var length = value.length;   
	        var mobile = /^(1(([35][0-9])|(77)|(47)|[8][01236789]))\d{8}$/;   
	        var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/; 
	        return this.optional(element) || tel.test(value) || (length==11 && mobile.test(value));   
	    }, "请输入正确的联系方式"); 
    
	    // 匹配qq      
	    jQuery.validator.addMethod("isQQ", function(value, element) {   
	    	var qq = /^\d{5,10}$/;      
	        return this.optional(element) || qq.test(value);
	    }, "请输入正确的QQ");
	    
	    $(document).ready(function(){
	    	$("#name").focus();
	    });
	    
	    $(function(){
			$("#form").validate();
		});
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 个人信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="user_updateMyInfo" id="form">
    <s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif"/>　
        	维护个人信息
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100px">登录名</td>
                        <td><s:textfield name="loginName" cssClass="InputStyle" disabled="true"/></td>
                    </tr>
                    <tr><td width="100px">所属部门</td>
                        <td><s:textfield name="department.name" cssClass="InputStyle" disabled="true"/></td>
                    </tr>
                    <tr><td width="100px">角色</td>
                        <<td><s:iterator value="roles">${name}&nbsp;</s:iterator></td>
                    </tr>
                    
                    <!-- 个人基本信息 -->
                    <tr><td width="100px">姓名</td>
                        <td><s:textfield name="name" id="name" cssClass="InputStyle"/><font color="red"> *</font></td>
                    </tr>
					<tr><td width="100px">性别</td>
                        <td>
                    		<s:radio name="gender" list="{'男','女'}"></s:radio>
						</td>
                    </tr>
                    <tr><td width="100px">出生日期</td>
                        <td><s:textfield name="birthday" cssClass="InputStyle Wdate" onclick="WdatePicker()"/></td>
                    </tr>
					<tr><td width="100px">联系电话</td>
                        <td><s:textfield name="phoneNumber" cssClass="InputStyle isTel"/></td>
                    </tr>
                    <tr><td width="100px">E-mail</td>
                        <td><s:textfield name="email" cssClass="InputStyle email"/></td>
                    </tr>
                     <tr><td width="100px">QQ</td>
                        <td><s:textfield name="QQ" cssClass="InputStyle isQQ"/></td>
                    </tr>
                    <tr><td width="100px">家庭住址</td>
                        <td><s:textfield name="address" cssClass="InputStyle"/></td>
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
	1、只能修改个人的基本信息，其他信息不能修改。<br/><br/>
</div>

</body>
</html>
