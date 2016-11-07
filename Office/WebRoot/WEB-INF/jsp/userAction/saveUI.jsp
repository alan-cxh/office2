<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>
	<title>用户设置</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <script type="text/javascript">
	     // 判断是否为合法字符(a-zA-Z0-9-_)登录名
	    jQuery.validator.addMethod("isLoginName", function(value, element) { 
	    	var loginName = /^[A-Za-z0-9_-]+$/;
	        return this.optional(element) || loginName.test(value);       
	    }, "请输入正确的登录名格式"); 
    			
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
	    	$("#department").focus();
	    });
		
		//开启验证
	    $(function() {
		    $("#userForm").validate({
		        messages:{
		        	departmentId:{required : "请选择所属部门"},
		            name:{required : "姓名不能为空",maxlength:jQuery.validator.format("允许的最大长度为{0}个字符")},
		            roles:{required : "请选择所属角色"},
		        }
		    });
		});
		
		
	/* 
    	$(document).ready(function(){
    		$("#departmentId").focus();
    		
    		 $("#userForm").submit(function(){
    		
    			if($.trim($("#departmentId").val()) == ""){
    				$("#departmentId").css("border-color","#E10E4F");
    				$("#departmentId").focus();
    				return false;
    			}else{
    				$("#departmentId").css("border-color","");
    			}
    			
    			if($.trim($("#loginName").val()) == ""){
    				$("#loginName").css("border-color","#E10E4F");
    				$("#logiName").focus();
    				return false;
    			}else{
    				$("#loginName").css("border-color","");
    			}
    			
    			if($.trim($("#name").val()) == ""){
    				$("#name").css("border-color","#E10E4F");
    				$("#name").focus();
    				return false;
    			}else{
    				$("#loginName").css("border-color","");
    			}
    			
    			
    			return true;
    			
    		}); 
    	});  */
    </script>
</head>
<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户设置
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="user_%{id == null ? 'add' : 'update'}" id="userForm">
    <s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />　
        	<s:if test="%{id == null}">添加用户</s:if>
        	<s:else>修改用户</s:else> </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100">所属部门</td>
                        <td>
                        	<!-- 动态显示要选择的上级部门 listKey="id"是提交时处理  ；list="#departmentList"是OGNL取值，listValue="name"是显示数据-->
                        	<s:select 
                        		name="departmentId" cssClass="SelectStyle required"
                        		list="#departmentList" listKey="id" listValue="name"
                        		headerKey="" headerValue="=======请选择所属部门=======" id="department">
                        	</s:select><font color="red"> *</font>
                        </td>
                    </tr>
                    <tr><td>登录名</td>
                    <s:if test="id == null">
                        <td><s:textfield name="loginName" id="loginName" cssClass="InputStyle {isLoginName:true,required:true,rangelength:[4,10]}"/><font color="red"> *</font></td>
                    </s:if>
					<s:else>
					<s:property value="loginName"/>
						 <td><s:textfield name="loginName" cssClass="InputStyle" disabled="true"/></td>
					</s:else>
                    	<td style="color: red"><s:fielderror name="loginName"></s:fielderror></td>
                    </tr>
                    <!-- 状态 -->
                    <s:if test="id != null">
                    	<tr><td>状态</td>
                        <td><s:radio list="#{'1':'正常','0':'停用'}" name="status"></s:radio> </td>
                    </tr>
                    </s:if>
                    <tr><td>姓名</td>
                        <td><s:textfield name="name" id="name" cssClass="InputStyle {required:true,maxlength:10}" /><font color="red"> *</font></td>
                    </tr>
					<tr><td>性别</td>
                        <td>
                        	<%-- 从性别字典中取出来  --%>
                    		<s:radio name="gender" list="#sexList" listKey="name" listValue="name"></s:radio>
						</td>
                    </tr>
                    <tr><td>民族</td>
                        <td>
                        	<%-- 从性别字典中取出来  --%>
                    		<s:select 
                        		name="nation" cssClass="SelectStyle"
                        		list="#nationList" listKey="name" listValue="name"
                        		headerKey="" headerValue="========请选择所属民族=======">
                        	</s:select>
						</td>
                    </tr>
                    
                    <tr><td>职称</td>
                        <td>
                    		<s:select name="jobTitle" cssClass="SelectStyle"
	                        		list="#jobTitleList" listKey="name" listValue="name"
	                        		headerKey="" headerValue="=========请选择职称========="
	                        />
						</td>
                    </tr>
                    <tr><td>出生日期</td>
                        <td><s:textfield name="birthday" id="birthday" cssClass="InputStyle Wdate" onclick="WdatePicker()"/></td>
                    </tr>
					<tr><td>联系电话</td>
                        <td><s:textfield name="phoneNumber" id="phoneNumber" cssClass="InputStyle isTel" /></td>
                    </tr>
                    <tr><td>E-mail</td>
                        <td><s:textfield name="email" id="email" cssClass="InputStyle email"/></td>
                    </tr>
                    <tr><td>QQ</td>
                        <td><s:textfield name="QQ" id="QQ" cssClass="InputStyle isQQ" /></td>
                    </tr>
                    
                     <tr><td>家庭住址</td>
                        <td><s:textfield name="address" cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td valign="top">备注</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />　岗位设置 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<td width="100" valign="top">角色</td>
                        <td>
                        	<s:select
								name="roleIds" multiple="true" size="10" cssClass="SelectStyle required"
                        		list="#roleList" listKey="id" listValue="name" id="roles">
                            </s:select>
                            <font color="#A33639">　　*按住Ctrl键可以多选或取消选择*</font>
                        </td>
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
	1、用户的登录名要唯一，在填写时要同时检测是否可用。<br/><br/>
	2、登录名由数字、字母、_、-、组成，且长度在4-10之间。<br/><br/>
	3、新建用户后，密码被初始化为"1234"。<br/><br/>
	4、密码在数据库中存储的是MD5摘要（不是存储明文密码）。<br/><br/>
	5、用户登录系统后可以使用"个人设置→修改密码"功能修改密码。<br/><br/>
	6、修改用户信息时，登录名不可修改。
</div>

</body>
</html>
