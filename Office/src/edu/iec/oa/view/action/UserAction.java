package edu.iec.oa.view.action;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.Department;
import edu.iec.oa.domain.Role;
import edu.iec.oa.domain.User;
import edu.iec.oa.domain.ZD_jobTitle;
import edu.iec.oa.domain.ZD_nation;
import edu.iec.oa.domain.ZD_sex;
import edu.iec.oa.util.DepartmentTreeUtil;
import edu.iec.oa.util.HqlHelper;

/**
 * @author TaoJG
 * 注入到IOC容器中
 * 多例获取
 */

@Controller
@Scope("prototype")
public class UserAction extends ModelDrivenBaseAction<User>{
	private static final long serialVersionUID = 1L;
	
	private Long departmentId;
	private Long[] roleIds;
	
	private String oldPassword;		//	原密码
	private String newPassword;		//	新密码
	private String code="";			//前台传来的验证码
	
	/**
	 * 查询条件
	 * status:状态2；正常
	 * 		   状态：1，停用
	 */
	
	private Long Vstatus;		//状态
	private String VloginName;	//登录名
	private String Vname;		//姓名
	private String VjobTitle;		//姓名
	
	
	/**列表*/
	public String list() throws Exception {
		
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentTreeUtil.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		//查询用户时：准备职称信息
		List<ZD_jobTitle> jobTitleList = zd_jobTitleService.findAll();
		ActionContext.getContext().put("jobTitleList", jobTitleList);
		
		//构建查询条件
		new HqlHelper(User.class, "u")
			//过滤条件
			.addWhereCondition(departmentId != null, "u.department=?", departmentService.getById(departmentId))//部门
			.addWhereCondition(Vstatus != null && Vstatus == 2, "u.status=?", User.STATUS_NORMAL)//状态正常
			.addWhereCondition(Vstatus != null && Vstatus == 1, "u.status=?", User.STATUS_INVALID)//状态停用
			.addWhereCondition(VloginName != null && VloginName.length() > 0, "u.loginName LIKE ?", "%"+VloginName+"%")//登录名
			.addWhereCondition(VjobTitle != null && VjobTitle.length() > 0, "u.jobTitle = ?", VjobTitle)//职称
			.addWhereCondition(Vname != null && Vname.length() > 0, "u.name LIKE ?", "%"+Vname+"%")//姓名
			.addOrderByProperty("u.id", true)//排序：升序
			.preparePageBean(userService, pageNum);
		
		// 2，查询分页信息
		return "list";
	}
	
	
	/**添加*/
	public String add() throws Exception {
		//判断登录名在数据库中是否存在
		if(userService.checkLoginName(model.getLoginName()) != null){
			addFieldError("loginName", "该用户已经存在，请重新输入");
			this.addUI();
			return "saveUI";
		}else{
			/**当model为实体时候，可直接封装，但要设置未封装的属性*/
			//设置所属部门
			model.setDepartment(departmentService.getById(departmentId));
			//设置所属角色
			List<Role> roleList = roleService.getByIds(roleIds);
			model.setRoles(new HashSet<Role>(roleList));//将List转为Set
			//新添加用户的初始密码为1234(但要对密码进行MD5摘要)
			String md5Digest = DigestUtils.md5Hex("1234");
			model.setPassword(md5Digest);
			model.setStatus(User.STATUS_NORMAL);//添加用户：初始状态为正常：1
			
			//性别默认为：男
			if(model.getGender() == null || model.getGender().equals("")){
				model.setGender("未知");
			}
			userService.save(model);
			return "toList";
		}
	}
	
	/**添加页面*/
	public String addUI() throws Exception {
		//添加用户时：准备所属部门信息
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentTreeUtil.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		//添加用户时：准备所属角色信息
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		
		
		//添加用户时：准备职称信息
		List<ZD_jobTitle> jobTitleList = zd_jobTitleService.findAll();
		ActionContext.getContext().put("jobTitleList", jobTitleList);
		
		//添加用户时：准备性别信息
		List<ZD_sex> sexList = zd_sexService.findAll();
		ActionContext.getContext().put("sexList", sexList);
		
		//添加用户时：准备民族信息
		List<ZD_nation> nationList = zd_nationService.findAll();
		ActionContext.getContext().put("nationList", nationList);
		
		return "saveUI";
	}
	
	/**修改*/
	public String update() throws Exception {
		//1,从数据库中读取要修改的原对象
		User user = userService.getById(model.getId());
		//2,设置要修改的属性
		//user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setBirthday(model.getBirthday());
		user.setNation(model.getNation());
		user.setJobTitle(model.getJobTitle());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setQQ(model.getQQ());
		user.setAddress(model.getAddress());
		user.setDescription(model.getDescription());
		user.setStatus(model.getStatus());
		
		user.setDepartment(departmentService.getById(departmentId));
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));
		//3,更新到数据库
		userService.update(user);
		return "toList";
	}
	
	/**修改页面*/
	public String updateUI() throws Exception {
		
		//修改用户时：准备所属部门信息
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentTreeUtil.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		//修改用户时：准备所属角色信息
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		//准备在页面上回显数据
		User user = userService.getById(model.getId());
		
		if(user.getDepartment() != null){
			departmentId = user.getDepartment().getId();
		}
		if(user.getRoles() != null){
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for(Role role : user.getRoles()){
				roleIds[index++] = role.getId();
			}
		}
		
		//添加用户时：准备性别信息
		List<ZD_sex> sexList = zd_sexService.findAll();
		ActionContext.getContext().put("sexList", sexList);
		
		//添加用户时：准备民族信息
		List<ZD_nation> nationList = zd_nationService.findAll();
		ActionContext.getContext().put("nationList", nationList);
		
		//添加用户时：准备民族信息
		List<ZD_jobTitle> jobTitleList = zd_jobTitleService.findAll();
		ActionContext.getContext().put("jobTitleList", jobTitleList);
		
		//将数据放到栈顶，回显数据
		ActionContext.getContext().getValueStack().push(user);
		
		return "saveUI";
	}
	
	/**删除*/
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}
	
	/**初始化密码*/
	public String initPassword() throws Exception {
		//1,从数据库中读取要修改的原对象
		User user = userService.getById(model.getId());
		//2,修改密码属性,（对初始化密码进行MD5摘要）
		String md5Digest = DigestUtils.md5Hex("1234");
		user.setPassword(md5Digest);
		//3,更新到数据库
		userService.update(user);
		return "toList";
	}
	
	/**登录页面*/
	public String loginUI() throws Exception {
		return "loginUI";
	}
	
	/**登录*/
	public String login() throws Exception {
		String returnString = "loginUI";
		HttpSession session = ServletActionContext.getRequest().getSession();//获取Session对象
		String sessionCode = (String) session.getAttribute("sessionCode");
		if(sessionCode.equals(code)){//首先判断验证码是否相等再判断用户名和密码
			User user = userService.getLoginNameAndPassword(model.getLoginName(),model.getPassword());
			if(user == null){//登录失败
				addFieldError("loginError", "用户名或密码错误!");
				returnString = "loginUI";
			}else{//登录成功
				ActionContext.getContext().getSession().put("user", user);//将user放到session中
				returnString = "toIndex";
			}
		}else{//验证码错误
			addFieldError("loginError", "验证码错误!");
			returnString = "loginUI";
		}
		return returnString;
	}
	
	/**注销*/
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	
	/**查看个人信息*/
	public String seeMyInfo() throws Exception {
		//从session中读取当前登录用户
		User sessionUser = (User) ActionContext.getContext().getSession().get("user");
		
		//因为个人信息是从session里面获取的，不能实时更新，所以应该获取他的ID从数据库里面读取信息
		User user = userService.getById(sessionUser.getId());
		ActionContext.getContext().getValueStack().push(user); // 将获取的对象放到栈顶，用于回显
		return "seeMyInfo";
	}
	
	/**修改登录密码页面*/
	public String updateMyPWDUI() throws Exception {
		//从session中读取当前登录用户
		User user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getValueStack().push(user); // 将获取的对象放到栈顶，用于回显
		return "updateMyPWDUI";
	}
	/**修改登录密码*/
	public String updateMyPWD() throws Exception {
		//从session中读取当前登录用户
		User user = (User) ActionContext.getContext().getSession().get("user");
		String oldMd5Digest = DigestUtils.md5Hex(getOldPassword());		//将前台传来的旧密码进行md5摘要
		String newMd5Digest = DigestUtils.md5Hex(getNewPassword());		//将前台传来的新密码进行md5摘要
		//判断旧密码与原密码是否相等
		if(oldMd5Digest.equals(user.getPassword())){
			user.setPassword(newMd5Digest);
			//保存新密码到数据库
			userService.update(user);
			ActionContext.getContext().getSession().remove("user");
			return "logout";
			
		}else{//否则返回当前修改密码页面，并提示错误信息
			addFieldError("message", "您输入的原密码错误！");
			updateMyPWDUI();
			return "updateMyPWDUI";
		}
		
	}
	/**修改个人信息页面*/
	public String updateMyInfoUI() throws Exception {
		//从session中读取当前登录用户
		User sessionUser = (User) ActionContext.getContext().getSession().get("user");
		
		//因为个人信息是从session里面获取的，不能实时更新，所以应该获取他的ID从数据库里面读取信息
		User user = userService.getById(sessionUser.getId());
		ActionContext.getContext().getValueStack().push(user); // 将获取的对象放到栈顶，用于回显
		return "updateMyInfoUI";
	}
	/**修改个人信息*/
	public String updateMyInfo() throws Exception {
		//获得修改对象
		User user = userService.getById(model.getId());
		
		//修改对应字段：个人基本信息
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setBirthday(model.getBirthday());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setQQ(model.getQQ());
		user.setAddress(model.getAddress());
		
		//更新到数据库
		userService.update(user);
		return "toSeeMyInfo";
	}
	
	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public Long[] getRoleIds() {
		return roleIds;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	public Long getVstatus() {
		return Vstatus;
	}


	public void setVstatus(Long vstatus) {
		Vstatus = vstatus;
	}


	public String getVloginName() {
		return VloginName;
	}


	public void setVloginName(String vloginName) {
		VloginName = vloginName;
	}


	public String getVname() {
		return Vname;
	}


	public void setVname(String vname) {
		Vname = vname;
	}


	public String getVjobTitle() {
		return VjobTitle;
	}


	public void setVjobTitle(String vjobTitle) {
		VjobTitle = vjobTitle;
	}
	
}
