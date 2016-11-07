//本js是判断所有提交表单中的数据是否合法
//比如用户名，密码不能为空的判断
//出生日期，电话号码的正则表达式等等


//登录页面的数据验证
function isValid_loginUI(form){
	if(form.loginName.value == ""){
		alert("用户名不能为空！");
		form.loginName.focus();
		return false;
	} 
	else if(form.password.value == ""){
		alert("密码不能为空！");
		form.password.focus();
		return false;
	}else{
		return true;
	}
}

//角色的新建，修改页面的数据验证
function isValid_saveRoleUI(form){
	if(form.name.value == ""){
		alert("角色名称不能为空！");
		form.name.focus();
		return false;
	}
	else if(form.name.value.indexOf(" ") >= 0){
		alert("角色名称不能含有空格！");
		form.name.focus();
		return false;
	}
	//将16个英文符替换成8个英文符
	else if((form.name.value).replace(/[^\x00-\xFF]/g, '**').length > 20){
		alert("角色名称不能超过10个中文符，20个英文符！");
		form.name.focus();
		return false;
	}
	else if((form.description.value).replace(/[^\x00-\xFF]/g, '**').length > 100){
		alert("角色描述不能超过50个中文符，100个英文符");
		form.description.focus();
		return false;
	}
	else{
		return true;
	}
}

//部门的新建，修改页面的数据验证
function isValid_saveDepartmentUI(form){
	if(form.name.value == ""){
		alert("部门名称不能为空！");
		form.name.focus();
		return false;
	}
	else if(form.name.value.indexOf(" ") >= 0){
		alert("部门名称不能含有空格！");
		form.name.focus();
		return false;
	}
	//将16个英文符替换成8个英文符
	else if((form.name.value).replace(/[^\x00-\xFF]/g, '**').length > 30){
		alert("部门名称不能超过15个中文符，30个英文符！");
		form.name.focus();
		return false;
	}
	else if((form.description.value).replace(/[^\x00-\xFF]/g, '**').length > 100){
		alert("部门描述不能超过50个中文符，100个英文符");
		form.description.focus();
		return false;
	}
	else{
		return true;
	}
}


//用户的新建，修改页面的数据验证
function isValid_saveUserUI(form){
	var regularLoginName = new RegExp("^[a-zA-Z0-9_-]{4,15}$");//登录名由数字、字母、下划线组成，长度为4到15位之间
	var regularPhoneNumber1 = new RegExp(
	"^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-3,5-9]))\\d{8}$"); // 手机
	var regularPhoneNumber2 = new RegExp(
	"^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$");// 座机
	var regularEmail = new RegExp(
	"^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"); // 邮箱
	if(form.loginName.value == ""){
		alert("登录名不能为空！");
		form.loginName.focus();
		return false;
	}
	else if(form.loginName.value.indexOf(" ") >= 0){
		alert("登录名不能含有空格！");
		form.loginName.focus();
		return false;
	}
	else if(!regularLoginName.test(form.loginName.value)){
		alert("登录名由数字、字母、下划线组成，长度为4到15位之间");
		form.loginName.focus();
		return false;
	}
	else if(form.name.value == ""){
		alert("姓名不能为空！");
		form.name.focus();
		return false;
	}
	else if(form.name.value.indexOf(" ") >= 0){
		alert("姓名不能含有空格！");
		form.name.focus();
		return false;
	}
	else if((form.name.value).replace(/[^\x00-\xFF]/g, '**').length > 16){
		alert("姓名不能超过8个中文符，16个英文符！");
		form.name.focus();
		return false;
	}
	else if(form.phoneNumber.value.length > 0 
		&& !regularPhoneNumber1.test(form.phoneNumber.value) 
		&& !regularPhoneNumber2.test(form.phoneNumber.value)){
		alert("请输入正确的电话号码！");
		form.phoneNumber.focus();
		return false;
	}
	else if(form.email.value.length > 0 && !regularEmail.test(form.email.value)){
		alert("请输入正确的邮箱格式！");
		form.email.focus();
		return false;
	}
	else if((form.description.value).replace(/[^\x00-\xFF]/g, '**').length > 200){
		alert("备注不能超过100个中文符，200个英文符！");
		form.description.focus();
		return false;
	}
	else if((form.roleIds == null) || (form.roleIds.value == "")){
		alert("请为该用户至少选择一个角色！");
		form.roleIds.focus();
		return false;
	}
	else{
		return true;
	}
}

//修改密码页面的数据验证
function isValid_updateMyPWDUI(form){
	if(form.oldPassword.value == ""){
		alert("原密码不能为空");
		form.oldPassword.focus();
		return false;
	}
	else if((form.oldPassword.value.length < 4) || (form.oldPassword.value.length > 15)){
		alert("密码的长度为4-15之间！");
		form.oldPassword.focus();
		return false;
	}
	else if(form.newPassword.value == ""){
		alert("新密码不能为空");
		form.newPassword.focus();
		return false;
	}
	else if(form.newPassword.value.indexOf(" ") >= 0){
		alert("新密码不能含有空格");
		form.newPassword.focus();
		return false;
	}
	else if(form.reNewPassword.value == ""){
		alert("重复密码不能为空！");
		form.reNewPassword.focus();
		return false;
	}
	else if((form.newPassword.value) != (form.reNewPassword.value)){
		alert("两次输入的密码不一致！");
		form.reNewPassword.focus();
		return false;
	}else{
		return true;
	}
}
//新建，修改公告页面的表单验证
function isValid_saveNoticeUI(form){
	if(form.title.value == ""){
		alert("公告标题不能为空！");
		form.title.focus();
		return false;
	}
	else if(form.title.value.replace(/[^\x00-\xFF]/g, '**').length > 50){
		alert("公告标题不能超过25个中文字符，50个英文符！");
		form.title.focus();
		return false;
	}
	else if(form.content.value == ""){
		alert("公告内容不能为空！");
		form.content.focus();
		return false;
	}
	else if(form.content.value.replace(/[^\x00-\xFF]/g, '**').length > 400){
		alert("公告标题不能超过200个中文字符，400个英文符！");
		form.content.focus();
		return false;
	}else{
		return true;
	}
}

//新建，修改公告页面的表单验证
function isValid_saveForumManageUI(form){
	if(form.name.value == ""){
		alert("版块名称不能为空！");
		form.name.focus();
		return false;
	}
	else if(form.name.value.replace(/[^\x00-\xFF]/g, '**').length > 50){
		alert("版块不能超过25个中文字符，50个英文符！");
		form.name.focus();
		return false;
	}
	else if(form.description.value.replace(/[^\x00-\xFF]/g, '**').length > 400){
		alert("版块描述不能超过200个中文字符，400个英文符！");
		form.description.focus();
		return false;
	}else{
		return true;
	}
}

//论坛回复添加，添加回复主贴页面的表单验证
function isValid_addReplyUI(form){
	if(form.content.value.replace(/[^\x00-\xFF]/g, '**').length > 65535){
		alert("版块不能超过32767个中文字符，65535个英文符！");
		form.content.focus();
		return false;
	}
	else{
		return true;
	}
}

//论坛主题添加，添加主贴页面的表单验证
function isValid_addTopicUI(form){
	if(form.title.value == ""){
		alert("主贴标题不能为空！");
		form.title.focus();
		return false;
	}
	else if(form.title.value.replace(/[^\x00-\xFF]/g, '**').length > 200){
		alert("标题不能超过200个中文字符，100个英文符！");
		form.title.focus();
		return false;
	}
	else if(form.content.value.replace(/[^\x00-\xFF]/g, '**').length > 65535){
		alert("内容不能超过32767个中文字符，65535个英文符！");
		form.content.focus();
		return false;
	}
	else{
		return true;
	}
}

//通讯录：修改，添加页面的数据验证isValid_saveAddrBookUI
function isValid_saveAddrBookUI(form){
	var regularPhoneNumber1 = new RegExp("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-3,5-9]))\\d{8}$"); // 手机
	var regularPhoneNumber2 = new RegExp("^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$");// 座机
	var regularEmail = new RegExp("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"); // 邮箱
	
	if(form.name.value == ""){
		alert("姓名不能为空！");
		form.name.focus();
		return false;
	}
	else if(form.name.value.replace(/[^\x00-\xFF]/g, '**').length > 20){
		alert("姓名不能超过10个中文字符，20个英文符！");
		form.name.focus();
		return false;
	}
	//判断电话号码的格式
	else if(form.phone.value.length > 0 && !regularPhoneNumber2.test(form.phone.value)){
			alert("请输入正确的电话号码！");
			form.phone.focus();
			return false;
	}
	else if(form.phone.value.length > 0 && !regularPhoneNumber1.test(form.mobilePhone.value)){
			alert("请输入正确的电话号码！");
			form.mobilePhone.focus();
			return false;
	}
	else if( (form.QQ.value.length>0) && ((form.QQ.value.length<5) || (form.QQ.value.length>10))){
		alert("请输入正确的QQ！");
		form.QQ.focus();
		return false;
	}
	else if(form.email.value.length > 0 && !regularEmail.test(form.email.value)){
		alert("请输入正确的邮箱格式！");
		form.email.focus();
		return false;
	}
	else{
		return true;
	}
}
