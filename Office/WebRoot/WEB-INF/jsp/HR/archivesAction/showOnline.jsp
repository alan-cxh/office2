<%@ page import="com.zhuozhengsoft.pageoffice.*" pageEncoding="UTF-8"%>
<%@ page import="edu.iec.oa.domain.*"%>

<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<html>
<head>
	<title>在线查看</title>
</head>
<%
	String path = (String)request.getAttribute("path");
	
	PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
	poCtrl.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须
	
	poCtrl.setAllowCopy(false);			//禁止拷贝
	poCtrl.setMenubar(false);			//隐藏菜单栏
	poCtrl.setOfficeToolbars(false);	//隐藏Office工具条
	poCtrl.setCustomToolbar(false);		//隐藏自定义工具栏 
	
	//设置页面的显示标题
	poCtrl.setCaption("在线安全浏览文件");
	
	//打开文件:只读
	poCtrl.webOpen(path, OpenModeType.docReadOnly, "张三");
	poCtrl.setTagId("PageOfficeCtrl"); 		//此行必须	 
 %>
<body>
<!-- 表单操作 -->
<div id="InputDetailBar">
	<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
</div>

<!--word显示区域-->
<div>
    <po:PageOfficeCtrl id="PageOfficeCtrl" />
</div>
</body>
</html>
    