<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<title>部署流程定义</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<script type="text/javascript">
		//开启验证
	    $(function() {
		    $("#form").validate({
		        messages:{
		        	upload:{
		        		required : "请选择部署文件",
		        		accept : "请选择后缀为.zip的文件"
		        	},
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 部署流程定义
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>

    <s:form action="processDefinition_add" enctype="multipart/form-data" width="100%" id="form">
    
        <div class="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 部署流程定义 </DIV>
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder" style="margin-left: 20px;">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm" width="100%">
                    <tr>
						<td>请选择流程定义文档(zip格式)</td>
                        <td><input type="file" name="upload" class="InputStyle {accept:'zip',required:true} " style="width:350px;" /> *</td>
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


<div class="Description">
	<font size="3" color="red">说明：</font><br><br />
	1、只接受zip扩展名的文件。<br><br />
	2、该zip文件是管理员（设计人员）根据审批需求定义的流程定义，包含了xx.jpdl和xx.png两个文件的压缩包。
</div>

</body>
</html>
	