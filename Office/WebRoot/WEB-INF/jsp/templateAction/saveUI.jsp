<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<title>文档模板管理</title>
    <%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
    <script type="text/javascript">
    	$(document).ready(function(){
    		$("[name=name]").focus();
    	});
    	
    	//开启验证
	    $(function() {
		    $("#form").validate({
		        messages:{
		        	name:{
		        		required : "请输入模板名称",
		        	},
		        	upload:{
		        		required : "请选择模板文件",
		        		accept : "请选择后缀为.doc或者.docx的文件"
		        	},
		        	processKey:{
		        		required : "请选择所用流程",
		        	}
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 文档模板管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>

    <s:form action="template_%{id == null ? 'add' : 'edit'}" enctype="multipart/form-data" id="form">
		<s:hidden name="id"></s:hidden>
		
		<s:if test="%{id != null}">
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 文档模板操作 </div> 
        </div>
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<tr>
						<td width="20%">
							<s:a action="template_editOnline?id=%{id}" cssStyle="text-decoration: underline">[点击在线编辑文档模板]</s:a>
						</td>
					</tr>
					<tr>
						<td width="20%">
							<s:a action="template_showOnline?id=%{id}" cssStyle="text-decoration: underline">[点击在线查看文档模板]</s:a>
						</td>
					</tr>
					<tr height="10px"><td><hr></td></tr>
					<tr>
						<td width="20%">
							<s:a action="template_download?id=%{id}" cssStyle="text-decoration: underline">[点击下载文档模板文件]</s:a>
						</td>
					</tr>
                </table>
            </div>
        </div>
		</s:if>

        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 模板基本信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
				   	<tr>
                        <td width="20%">模板名称</td>
                        <td><s:textfield name="name" cssClass="InputStyle required" /><font color="red"> *</font></td>
                    </tr>
                    <tr>
                        <td width="20%">所用流程</td>
                        <td>
                        	<s:select name="processKey" cssClass="SelectStyle required"
                        		list="processDefinitionList" listKey="key" listValue="key"
                        		headerKey="" headerValue="==请选择所用流程=="
                        	/> <font color="red"> *</font>
						</td>
                    </tr>
                </table>
            </div>
        </div>
		
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 模板文件 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
				   	<tr>
                        <td width="25%">请选择文件(doc、docx格式)</td>
                        <s:if test="%{id == null}">
                        	<td><input type="file" name="upload" class="InputStyle required {accept:'doc,docx'}" style="width:450px;" /> </td>
                        </s:if>
                        <s:else>
                        	<td><input type="file" name="upload" class="InputStyle {accept:'doc,docx'}" style="width:450px;" /> </td>
                        </s:else>
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
	<font size="3" color="red">说明：</font><br><br/>
	1、模板文件是doc、docx扩展名的文件（Word文档）。<br /><br />
	2、如果是添加：必须要选择模板文件。<br /><br />
	3、如果是修改：只是在 需要更新模板文件时 才选择一个文件。<br /><br />
	4、填写的模板名称作为下载模板的文件名，所以请认真填写<br /><br />
	5、修改模板可以在线修改，不用添加新的模板，添加新模板将会覆盖原来的模板<br /><br />
</div>

</body>
</html>
    