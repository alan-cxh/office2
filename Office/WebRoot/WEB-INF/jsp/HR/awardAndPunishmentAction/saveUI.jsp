<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>奖惩记录管理</title>
	<%@ include file="/WEB-INF/jsp/publicPage/commons.jspf" %>
	<script type="text/javascript">
		$(document).ready(function(){
	    	$("#userId").focus();
	    });
		
		//开启验证
	    $(function() {
		    $("#form").validate({
		        messages:{
		        	userId:{required : "请选择教工姓名"},
		            type:{required : "请选择奖惩类型"},
		            upload:{
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 奖惩记录设置
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
	<!-- 将修改页面和添加页面合并，通过三目运算符判断id是否为空来判断action是添加还是修改 id为空时添加-->
    <s:form action="awardAndPunishment_%{id == null ? 'add' : 'update'}" enctype="multipart/form-data" id="form">
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
        	 <s:if test="%{id == null}">添加奖惩记录</s:if>
        	 <s:else>修改奖惩记录信息</s:else>
        	</DIV>
        	
        <s:if test="%{id != null}">
        <s:if test="%{proofSavePath != null && proofSavePath != ''}">
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<tr>
						<td width="20%">
							<s:a action="awardAndPunishment_editOnline?id=%{id}" cssStyle="text-decoration: underline">[点击在线编辑文档模板]</s:a>
						</td>
					</tr>
					<tr>
						<td width="20%">
							<s:a action="awardAndPunishment_download?id=%{id}" cssStyle="text-decoration: underline">[点击下载文档模板文件]</s:a>
						</td>
					</tr>
                </table>
            </div>
        </div>
		</s:if>
		</s:if>
        
        <s:if test="%{id != null}">
        <s:if test="%{proofSavePath != null && proofSavePath != ''}">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 奖惩信息 </div> 
        </div>
        </s:if>
        </s:if>
        	
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<tr>
                        <td width="100">奖/惩人员</td>
                        <td>
                        <!-- 动态显示要选择的上级部门 listKey="id"是提交时处理  ；list="#departmentList"是OGNL取值，listValue="name"是显示数据-->
                        	<s:if test="%{id == null}">
                        	<s:select 
                        		name="userId" cssClass="SelectStyle required"
                        		list="#userList" listKey="id" listValue="name"
                        		headerKey="" headerValue="==========请选择职工========" id="userId">
                        	</s:select><font color="red"> *</font>
                        	</s:if>
                        	<!-- 修改奖惩记录的时候，员工不能修改 -->
                        	<s:else>
                        		<s:textfield name="userName" cssClass="InputStyle" disabled="true"></s:textfield>
                        	</s:else>
						</td>
                    </tr>
                    <tr>
                        <td width="100">奖/惩类型</td>
                       <td><s:radio name="type" list="#{0:'奖励',1:'惩处'}"></s:radio></td> 
                    </tr>
                    <tr>
                        <td width="100">奖/惩时间</td>
                        <td><s:textfield name="time" cssClass="InputStyle Wdate" onclick="WdatePicker()"></s:textfield></td>
                    </tr>
                    <tr>
                        <td width="100" valign="top">奖/惩内容</td>
                        <td><s:textarea name="content" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                    <tr>
                        <td width="100" valign="top">奖/惩原因</td>
                        <td><s:textarea name="reson" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                    <!-- 判断：如果id不为空，则表示是修改，则要显示已经上传的附件 -->
                    <s:if test="%{id != null}">
                    	<s:if test="%{proofSavePath != null && proofSavePath != ''}">
	                    	<tr>
		                    	<td>已传附件</td>
		                        <td><s:a action="awardAndPunishment_download?id=%{id}">${userName}__奖惩记录</s:a></td>
		                    </tr>
		                    <td width="100">重传附件</td>
		                   	<td>
		                    	<input type="file" name="upload" class="InputStyle">
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
	1、奖惩人员的姓名、奖惩类型不能为空。<br/><br/>
	2、请填写相关属性的正确格式。<br/><br/>
	3、如果是修改奖惩记录，上传文件将会替换以前的文件，不上传不影响之前的文件<br/>
</div>

</body>
</html>

