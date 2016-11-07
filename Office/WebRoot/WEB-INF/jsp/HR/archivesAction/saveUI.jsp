<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>档案管理</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<script type="text/javascript">
		$(function(){
			$("#button").click(function(){
				var html = $("<input type='file' name='file'>");
				var delButton = $("<input type='button' name='button' value='删除'>");
				$("#more").append(html).append("&nbsp;").append(delButton).append("<br>");
				
				delButton.click(function(){
					html.remove();
					delButton.remove();
				});
			});
		});
		
		$(document).ready(function(){
    		$("#userId").focus();
    	});
    	
    	//开启验证
	    $(function() {
		    $("#form").validate({
		        messages:{
		        	userId:{
		        		required : "请选择教工",
		        	},
		        	title:{
		        		required : "档案名称不能为空",
		        	},
		        	upload:{
		        		required : "请选择上传档案",
		        		accept : "请选择后缀为.doc或者.docx的文件"
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 档案设置
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
	<!-- 将修改页面和添加页面合并，通过三目运算符判断id是否为空来判断action是添加还是修改 id为空时添加-->
    <s:form action="archives_%{id == null ? 'add' : 'update'}" enctype="multipart/form-data" method="post" id="form">
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
        	 <s:if test="%{id == null}">添加档案</s:if>
        	 <s:else>修改档案</s:else>
        	</DIV>  
        </div>
        
        <s:if test="%{id != null}">
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<tr>
						<td width="20%">
							<s:a action="archives_editOnline?id=%{id}" cssStyle="text-decoration: underline">[点击在线编辑文档模板]</s:a>
						</td>
					</tr>
					<tr>
						<td width="20%">
							<s:a action="archives_download?id=%{id}" cssStyle="text-decoration: underline">[点击下载文档模板文件]</s:a>
						</td>
					</tr>
                </table>
            </div>
        </div>
		</s:if>
        
        <s:if test="%{id != null}">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 档案信息 </div> 
        </div>
        </s:if>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<s:if test="%{id != null}">
                		<tr>
                			<td width="100">职工姓名</td>
                			<td width="100"><s:textfield name="user.name" cssClass="InputStyle" disabled="true"></s:textfield></td>
                		</tr>
                	</s:if>
                	<s:else>
	                	<tr>
	                        <td width="100">职工姓名</td>
	                        <td>
	                        	<!-- 动态显示要选择的上级部门 listKey="id"是提交时处理  ；list="#departmentList"是OGNL取值，listValue="name"是显示数据-->
	                        	<s:select 
	                        		name="userId" cssClass="SelectStyle required"
	                        		list="#userList" listKey="id" listValue="name"
	                        		headerKey="" headerValue="=========请选择职工=========">
	                        	</s:select><font color="red"> *</font>
	                        </td>
	                    </tr>
                    </s:else>
                    <tr>
                        <td width="100">档案名称</td>
                        <td><s:textfield name="title" cssClass="InputStyle required"></s:textfield><font color="red"> *</font></td>
                    </tr>
                    
                    <tr height="20px"></tr>
                    
                    <tr>
                        <td valign="top">档案描述</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                    <s:if test="%{id != null}">
	                    <tr>
	                    	<td>已传档案</td>
	                        <td>${user.loginName}_${user.name}_档案</td>
	                    </tr>
	                    <tr>
	                    	<td width="100">重传档案</td>
		                   	<td><input type="file" name="upload" class="InputStyle {accept:'doc,docx'}"></td>
                    	</tr>
                    </s:if>
                    <s:else>
	                    <tr>
	                    	<td width="100">上传档案</td>
		                   	<td>
		                    	<input type="file" name="upload" class="InputStyle {accept:'doc,docx',required:true}"><font color="red"> *</font>
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
	1、职工姓名和档案标题均不能为空。<br/><br/>
	2、下载的的档案文件的默认文件名为："登录名_教工姓名_档案"，如: "0701_陶然_档案"。<br/><br/>
	3、修改的时候，新上传的档案将会覆盖以前的档案，否则还是原来的档案<br/>
</div>

</body>
</html>

