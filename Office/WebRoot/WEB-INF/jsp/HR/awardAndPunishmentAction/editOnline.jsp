<%@ page import="com.zhuozhengsoft.pageoffice.*" pageEncoding="UTF-8"%>
<%@ page import="edu.iec.oa.domain.*"%>

<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<html>
<head>
	<title>在线编辑</title>
</head>
<%
	String path = (String)request.getAttribute("path");
	Long id = (Long)request.getAttribute("id");
	
	PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
	poCtrl.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须
	
	poCtrl.setAllowCopy(true);			//允许拷贝
	poCtrl.setOfficeToolbars(true);		//允许Office工具条
	
	poCtrl.setMenubar(false);			//不允许菜单栏
	poCtrl.setCustomToolbar(false);		//不允许自定义工具栏 
	
	//设置页面的显示标题
	poCtrl.setCaption("在线安全编辑文件");
	
	
	poCtrl.setSaveFilePage("awardAndPunishment_saveAwardAndPunishment.action?id="+id);//如要保存文件，此行必须:后台处理
	poCtrl.addCustomToolButton("保存", "save()", 1);//添加自定义工具栏按钮
	
	
	//打开文件:编辑
	poCtrl.webOpen(path, OpenModeType.docNormalEdit, "张三");
	poCtrl.setTagId("PageOfficeCtrl"); 		//此行必须	 
 %>
<body>
<!-- 表单操作 -->
<div id="InputDetailBar">
	<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
</div>

<!--显示表单内容-->
<div>
	<script type="text/javascript">
		function save() {
            document.getElementById("PageOfficeCtrl").WebSave();
            alert("保存成功");
        }
	</script>
    <po:PageOfficeCtrl id="PageOfficeCtrl" />
</div>
</body>
</html>
    