<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>
	<title>用户详细信息</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
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
    <s:form>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />　
        	查看用户详细信息
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100">所属部门</td>
                        <td>
                        	<!-- 动态显示要选择的上级部门 listKey="id"是提交时处理  ；list="#departmentList"是OGNL取值，listValue="name"是显示数据-->
                        	<s:textfield name="department.name" cssClass="InputStyle" disabled="true"></s:textfield>
                        </td>
                    </tr>
                    <tr><td>登录名</td>
						 <td><s:textfield name="loginName" id="loginName" cssClass="InputStyle" disabled="true"/></td>
                    </tr>
                    <tr><td>姓名</td>
                        <td><s:textfield name="name" id="name" cssClass="InputStyle" disabled="true"/><font color="red"> *</font></td>
                    </tr>
					<tr><td>性别</td>
                        <td>
                    		<s:radio name="gender" list="{'男','女'}"></s:radio>
						</td>
                    </tr>
                    <tr><td>出生日期</td>
                        <td><s:textfield name="birthday" id="birthday" cssClass="InputStyle Wdate" disabled="true"/>
                        </td>
                    </tr>
					<tr><td>联系电话</td>
                        <td><s:textfield name="phoneNumber" id="phoneNumber" cssClass="InputStyle isTel" disabled="true"/></td>
                    </tr>
                    <tr><td>E-mail</td>
                        <td><s:textfield name="email" id="email" cssClass="InputStyle email" disabled="true"/></td>
                    </tr>
                    <tr><td>QQ</td>
                        <td><s:textfield name="QQ" id="QQ" cssClass="InputStyle isQQ" disabled="true"/></td>
                    </tr>
                    
                     <tr><td>家庭住址</td>
                        <td><s:textfield name="address" cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td valign="top">备注</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle" disabled="true"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />　岗位信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<td width="100" valign="top">角色</td>
                        <td>
                            <s:iterator value="roles">${name}&nbsp;</s:iterator>
                            <font color="#A33639"></font>
                        </td>
                    </tr>
                </table>
            </div>
        </div>		
		
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

</body>
</html>
