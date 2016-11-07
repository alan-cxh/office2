<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>
	<title>公共通讯录设置</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <script type="text/javascript">
	    // 手机号码验证    
	    jQuery.validator.addMethod("isMobile", function(value, element) {    
	      var length = value.length;  
	      var mobile = /^(1(([35][0-9])|(77)|(47)|[8][01236789]))\d{8}$/; 
	      return this.optional(element) || (length == 11 && mobile.test(value));    
	    }, "请输入正确的手机号码");
	
	    // 电话号码验证    
	    jQuery.validator.addMethod("isPhone", function(value, element) {    
	      var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;   
	      return this.optional(element) || (tel.test(value));    
	    }, "请输入正确的电话号码");
	    // 邮政编码验证    
	    jQuery.validator.addMethod("isZipCode", function(value, element) {    
	      var zip = /^[1-9][0-9]{5}$/;    
	      return this.optional(element) || (zip.test(value));    
	    }, "请输入正确的邮编");
	    // 匹配qq      
	    jQuery.validator.addMethod("isQQ", function(value, element) {   
	    	var qq = /^\d{5,10}$/;      
	        return this.optional(element) || qq.test(value);
	    }, "请输入正确的QQ");
	    
	    $(document).ready(function(){
	    	$("#name").focus();
	    });
	    
	    //对表单进行验证
	    $(function(){
	    	$("#form").validate({
	    		message:{
	    			name:{required:"请输入联系人姓名"},
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 公共通讯录
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="publicAddrBook_%{id == null ? 'add' : 'update'}" id="form">
    <s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />　
        	<s:if test="%{id == null}">添加通讯录</s:if>
        	<s:else>修改通讯录</s:else> </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100px">姓名</td>
                        <td><s:textfield name="name" id="name" cssClass="InputStyle required"/><font color="red"> *</font>
                    </tr>
					<tr><td width="100px">性别</td>
                        <td>
                    		<s:radio name="gender" list="{'男','女'}"></s:radio>
						</td>
                    </tr>
					<tr><td width="100px">固定电话</td>
                        <td><s:textfield name="phone" cssClass="InputStyle isPhone"/></td>
                    </tr>
                    <tr><td width="100px">移动电话</td>
                        <td><s:textfield name="mobilePhone" cssClass="InputStyle isMobile"/></td>
                    </tr>
                     <tr><td width="100px">QQ</td>
                        <td><s:textfield name="QQ" cssClass="InputStyle isQQ"/></td>
                    <tr><td width="100px">E-mail</td>
                        <td><s:textfield name="email" cssClass="InputStyle email"/></td>
                    </tr>
                </table>
            </div>
        </div>
        
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />　其他信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100px">单位</td>
                        <td><s:textfield name="deparment" cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td width="100px">职务</td>
                        <td><s:textfield name="role" cssClass="InputStyle"/></td>
                    </tr>
					<tr><td width="100px">邮编</td>
                      	<td><s:textfield name="postCode" cssClass="InputStyle isZipCode"/></td>
                    </tr>
					<tr><td width="100px">爱好</td>
                        <td><s:textfield name="hobby" cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td width="100px">生日</td>
                        <td><s:textfield name="birthday" cssClass="Wdate InputStyle" onClick="WdatePicker({dateFmt:'MM月-dd日'})"/></td>
                    </tr>
                     <tr><td width="100px">通讯地址</td>
                        <td><s:textfield name="address" cssClass="InputStyle"/></td>
                    <tr><td valign="top" width="100px">备注</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
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
	1、填写信息时请填写相应的正确格式。<br/><br/>
</div>

</body>
</html>
