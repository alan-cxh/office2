<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>OA通讯录列表</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <script type="text/javascript">
	 	function showDetail(tId) {
			tId = encodeURI(tId);
			var url = "oaAddrBook_detailInfoUI.action?id=" + tId + "&t=" + new Date();
			window.showModalDialog(url, null,"dialogHeight:300px;dialogWidth:600px;resizable:yes");
		}
    </script>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> OA通讯录
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
<!--按指定条件查询-->
	<s:form action="oaAddrBook_list" method="post" id="pageForm">
		<div id="TableTail" style="margin-left: -50px"> 
			<div id="TableTail_inside">
				<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left" style="padding-bottom: 10px">
					<tr valign="bottom" style="padding-bottom: 10px">
						<td></td>
						<td>
							部门：
							<s:select name="departmentId"
	                        		list="#departmentList" listKey="id" listValue="name"
	                        		headerKey="" headerValue="==请选择部门=="
	                        />
							登录名：
							<s:textfield name="VloginName"></s:textfield>
							姓名：
							<s:textfield name="Vname"></s:textfield>
	                                                                        电话：
							<s:textfield name="Vphone"></s:textfield>
							<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG" align="ABSMIDDLE"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
	
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="10%">姓名</td>
                <td width="10%">性别</td>
                <td width="15%">联系电话</td>
                <td width="15%">QQ</td>
                <td width="15%">邮箱</td>
                <td width="20%">地址</td>
                <td>相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        <s:iterator value="recordList">
            <tr class="TableDetail1 template">
                <td align="center">${name}&nbsp;</td>
                <td align="center">${gender}&nbsp;</td>
                <td align="center">${phoneNumber}&nbsp;</td>
                <td align="center">${QQ}&nbsp;</td>
                <td align="center">${email}&nbsp;</td>
                <td>${address}&nbsp;</td>
                <td align="center">&nbsp;
                	<s:a action="oaAddrBook_detailInfoUI?id=%{id}">查看详细信息</s:a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    
   <!-- 其他功能超链接，也就是新建 -->
    <div style="height: 2px;background-color: #3E88B9;">
    </div>
</div>

<!--分页信息-->
 <%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %> 
<!-- 说明 -->
<div class="Description">
	<font size="3" color="red">说明：</font><br><br/>
	1、点击"查看详细信息可查看详细通讯录"。<br/><br/>
</div>
</html>
