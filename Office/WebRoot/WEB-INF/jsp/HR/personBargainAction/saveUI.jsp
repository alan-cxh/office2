<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>人事合同管理</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<script type="text/javascript">
		$(document).ready(function(){
	    	$("#userName").focus();
	    });
		
		//开启验证
	    $(function() {
		    $("#form").validate({
		        messages:{
		        	userId:{required : "请选择教工姓名"},
		            title:{required : "请输入合同名称"},
		            type:{required : "请选择合同类型"},
		            upload:{accept : "请选择后缀名为doc或者docx的文件"}
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 人事合同设置
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
	<!-- 将修改页面和添加页面合并，通过三目运算符判断id是否为空来判断action是添加还是修改 id为空时添加-->
    <s:form action="personBargain_%{id == null ? 'add' : 'update'}" enctype="multipart/form-data" id="form">
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
        	 <s:if test="%{id == null}">添加人事合同</s:if>
        	 <s:else>修改人事合同信息</s:else>
        	</DIV>  
        </div>
        
        <s:if test="%{id != null}">
        <s:if test="%{bargainSavePath != null && bargainSavePath != ''}">
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<tr>
						<td width="20%">
							<s:a action="personBargain_editOnline?id=%{id}" cssStyle="text-decoration: underline">[点击在线编辑文档模板]</s:a>
						</td>
					</tr>
					<tr>
						<td width="20%">
							<s:a action="personBargain_download?id=%{id}" cssStyle="text-decoration: underline">[点击下载文档模板文件]</s:a>
						</td>
					</tr>
                </table>
            </div>
        </div>
		</s:if>
		</s:if>
		
		<s:if test="%{id != null}">
		<s:if test="%{bargainSavePath != null && bargainSavePath != ''}">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 合同信息 </div> 
        </div>
        </s:if>
        </s:if>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<tr>
                        <td width="100">教工姓名</td>
                        <td>
                        <!-- 动态显示要选择的上级部门 listKey="id"是提交时处理  ；list="#departmentList"是OGNL取值，listValue="name"是显示数据-->
                        	<s:if test="%{id == null}">
                        	<s:select 
                        		name="userId" cssClass="SelectStyle required"
                        		list="#userList" listKey="id" listValue="name" id="userName"
                        		headerKey="" headerValue="==========请选择职工=========">
                        	</s:select><font color="red"> *</font>
                        	</s:if>
                        	<!-- 修改人事合同的时候，员工不能修改 -->
                        	<s:else>
                        		<s:textfield name="userName" cssClass="InputStyle" disabled="true"></s:textfield>
                        	</s:else>
						</td>
                    </tr>
                    </tr>
                        <td width="100">合同名称</td>
                        <td><s:textfield name="title" cssClass="InputStyle required"></s:textfield><font color="red"> *</font></td>
                    </tr>
                    <tr>
                        <td width="100">合同类型</td>
                        <td>
							<s:select name="type"
	                        		list="#bargainTypeList" listKey="name" listValue="name" cssClass="SelectStyle required"
	                        		headerKey="" headerValue="========请选择合同类型========"
	                        /><font color="red"> *</font>
                        </td> 
                    </tr>
                        <td width="100" valign="top">合同描述</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                    <!-- 判断：如果id不为空，则表示是修改，则要显示已经上传的附件 -->
                    <s:if test="%{id != null}">
                    	<s:if test="%{bargainSavePath != null && bargainSavePath != ''}">
	                    	<tr>
		                    	<td>已传附件</td>
		                        <td><s:a action="personBargain_download?id=%{id}">${userName}__${title}</s:a></td>
		                    </tr>
		                    <td width="100">重传附件</td>
		                   	<td>
		                    	<input type="file" name="upload" class="InputStyle {accept:'doc,docx'}">
		                   	</td>
                    	</s:if>
                    	<s:else>
		                    <tr>
		                    	<td width="100">上传附件</td>
			                   	<td><input type="file" name="upload" class="InputStyle {accept:'doc,docx'}"></td>
	                    	</tr>
                    	</s:else>
                    </s:if>
                    
                    <s:else>
	                    <tr>
	                    	<td width="100">上传附件</td>
		                   	<td>
		                    	<input type="file" name="upload" class="InputStyle {accept:'doc,docx'}">
		                   	</td>
	                    </tr>
                    </s:else>
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
	1、教工的姓名、合同名称、合同类型不能为空。<br/><br/>
	2、请填写相关属性的正确格式。<br/><br/>
	3、修改的时候，新上传的附件将会覆盖以前的附件，否则还是原来的附件。<br/><br/>
	4、下载文件的默认文件名为："教工姓名__合同名称"。<br/><br/>
	5、没有显示已上传文件表示没有上传文件。<br/><br/>
</div>

</body>
</html>

