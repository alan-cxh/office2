<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>
	<title>个人通讯录</title>
	
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/My97DatePicker/WdatePicker.js"></script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 个人通讯录
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />　
        	查看通讯录详细信息
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100px">姓名</td>
                        <td><s:textfield name="name" cssClass="InputStyle" disabled="true"/></td>
                    </tr>
					<tr><td width="100px">性别</td>
                        <td>
                    		<s:radio name="gender" list="{'男','女'}"></s:radio>
						</td>
                    </tr>
					<tr><td width="100px">固定电话</td>
                        <td><s:textfield name="phone" cssClass="InputStyle" disabled="true"/></td>
                    </tr>
                    <tr><td width="100px">移动电话</td>
                        <td><s:textfield name="mobilePhone" cssClass="InputStyle" disabled="true"/></td>
                    </tr>
                     <tr><td width="100px">QQ</td>
                        <td><s:textfield name="QQ" cssClass="InputStyle"/></td>
                    <tr><td width="100px">E-mail</td>
                        <td><s:textfield name="email" cssClass="InputStyle" disabled="true"/></td>
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
                        <td><s:textfield name="deparment" cssClass="InputStyle" disabled="true"/></td>
                    </tr>
                    <tr><td width="100px">职务</td>
                        <td><s:textfield name="role" cssClass="InputStyle" disabled="true"/></td>
                    </tr>
					<tr><td width="100px">邮编</td>
                      	<td><s:textfield name="postCode" cssClass="InputStyle" disabled="true"/></td>
                    </tr>
					<tr><td width="100px">爱好</td>
                        <td><s:textfield name="hobby" cssClass="InputStyle" disabled="true"/><font color="#A33639"></font></td>
                    </tr>
                    <tr><td width="100px">生日</td>
                        <td><s:textfield name="birthday" cssClass="Wdate InputStyle" disabled="true"/></td>
                    </tr>
                     <tr><td width="100px">通讯地址</td>
                        <td><s:textfield name="address" cssClass="InputStyle" disabled="true"/></td>
                    <tr><td valign="top" width="100px">备注</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle" disabled="true"></s:textarea></td>
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

<!-- 分页信息 -->

</body>
</html>
