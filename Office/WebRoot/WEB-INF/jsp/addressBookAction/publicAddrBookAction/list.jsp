<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>公共通讯录</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <script type="text/javascript">
    	function showDetail(tId) {
			tId = encodeURI(tId);
			var url = "publicAddrBook_detailInfoUI.action?id=" + tId + "&t=" + new Date();
			window.showModalDialog(url, null,"dialogHeight:300px;dialogWidth:600px;resizable:yes");
		}
    </script>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 公共通讯录管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
<!--按指定条件查询-->
	<s:form action="publicAddrBook_list" method="post" id="pageForm">
		<div id="TableTail" style="margin-left: -50px"> 
			<div id="TableTail_inside">
				<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left" style="padding-bottom: 10px">
					<tr valign="bottom" style="padding-bottom: 10px">
						<td></td>
						<td>
							姓名：
							<s:textfield name="Vname"></s:textfield>
							性别：
							<s:select name="Vgender" list="#{'':'==性别==','男':'男','女':'女'}"/>
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
                <td width="5%">性别</td>
                <td width="10%">手机号码</td>
                <td width="15%">单位</td>
                <td width="10%">生日</td>
                <td width="15%">通讯地址</td>
                <td width="15%">备注</td>
                <td>相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        
        <s:iterator value="recordList">
            <tr class="TableDetail1 template">
                <td align="center">${name}&nbsp;</td>
                <td align="center">${gender}&nbsp;</td>
                <td align="center">${mobilePhone}&nbsp;</td>
                <td align="center">${deparment}&nbsp;</td>
                <td align="center">${birthday}&nbsp;</td>
                <td align="center">${address}&nbsp;</td>
                <td align="center">${description}&nbsp;</td>
                <td align="center">&nbsp;
	                <s:a action="publicAddrBook_delete?id=%{id}" onClick="return delConfirm()">删除</s:a>&nbsp;
	                <s:a action="publicAddrBook_updateUI?id=%{id}">修改</s:a>&nbsp;
               	    <s:a action="publicAddrBook_detailInfoUI?id=%{id}">查看详细信息</s:a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="publicAddrBook_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
</div>

<!--分页信息-->
 <%@ include file="/WEB-INF/jsp/publicPage/pageView.jspf" %> 
 
<!-- 说明 -->
<div class="Description">
	<font size="3" color="red">说明：</font><br><br />
	1、公共通讯录所有内部用户都可以添加。<br/><br/>
	2、"查看详细信息"可查看详细通讯录信息。<br/><br>
</div>

</body>
</html>
