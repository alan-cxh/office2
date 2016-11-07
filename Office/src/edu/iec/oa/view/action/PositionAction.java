package edu.iec.oa.view.action;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.Department;
import edu.iec.oa.domain.Position;
import edu.iec.oa.domain.Role;
import edu.iec.oa.domain.User;
import edu.iec.oa.util.DepartmentTreeUtil;
import edu.iec.oa.util.HqlHelper;

/**
 * @author TaoJG
 * 注入到IOC容器中
 * 多例获取
 * 职位更变Action
 */

@Controller
@Scope("prototype")
public class PositionAction extends ModelDrivenBaseAction<Position>{
	private static final long serialVersionUID = 1L;
	
	
	private Long departmentId;	//前台传来的部门的Id
	private Long[] roleIds;		//前台传来的角色Id
	private String roleName;	//拼接起来的角色名称字符串：保存在以前的角色属性中
	
	private Long userId;		//前台传来的用户的Id
	
	private String Vname;	//用于查找的用户名
	
	/**列表 :应该先显示用户的信息*/
	public String list() throws Exception {
//		List<User> userList = userService.findAll();//获取所有User对象
//		ActionContext.getContext().put("userList", userList);//将User对象 放到值栈中
		//准备回显查询条件的数据：部门，树状显示
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentTreeUtil.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		
		//构建查询条件
		new HqlHelper(User.class, "u")
			//过滤条件
			.addWhereCondition(departmentId != null, "u.department=?", departmentService.getById(departmentId))//部门
			.addWhereCondition(Vname != null && Vname.length() > 0, "u.name LIKE ?", "%"+Vname+"%")//姓名
			.addOrderByProperty("u.id", true)//排序：升序
			.preparePageBean(userService, pageNum);
		return "list";
	}

	/**添加职位变更记录页面*/
	public String addUI() throws Exception {
		//根据前台传来的userId，获取当前用户的基本信息
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String id = request.getParameter("userId") ; //给用户添加职位变更前台传来的userId
		
		User userInfo = userService.getById(userId);//给用户添加职位变更前台传来的userId
		//将当前添加的用户的信息放到栈顶,回显
		ActionContext.getContext().getValueStack().push(userInfo);
		
		//准备角色，部门等信息，用来选择填写
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
//		List<Department> departmentList = departmentService.findAll();
//		ActionContext.getContext().put("departmentList", departmentList);
		
		//树状获取部门信息
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentTreeUtil.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		return "saveUI";
	}

	
	/**添加*/
	public String add() throws Exception {
		//当model为实体的时候，可直接封装，但要设置未封装的属性；已封装的属性只有变更原因和变更时间：reason，changeTime属性
		System.out.println("后台add："+userId);
		if(userId != null){
			User user = userService.getById(userId);
			model.setUser(user);									//所对应的职工
			model.setNewDepartment(user.getDepartment().getName());		//现在的部门名称
			
			List<Role> role = new ArrayList<Role>(user.getRoles());	//将set类型的Role转为List
			for(int i=0; i<role.size(); i++){
				if(i == 0){
					model.setNewRole(role.get(0).getName());
				}else{
					model.setNewRole(model.getNewRole()+"、"+role.get(i).getName());//将角色拼接成字符串保存
			    }
		    }
		}
		model.setOperator(getCurrentUser().getName());  //操作者
		model.setOperatTime(new Date());			    //操作时间
		model.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());//操作的地址：电脑IP
	
		//根据前台选择的部门传来的id获得部门名称
		if(departmentId != null){
			String departmentName = departmentService.getById(departmentId).getName();
	     	model.setOldDepartment(departmentName);		//以前的部门
		}
		
     	//根据前台选择的角色传来的Id获得角色名称
		if(roleIds != null){
	     	List<Role> roleList = roleService.getByIds(roleIds);
	     	for(int i=0; i<roleList.size(); i++){
	     		if(i == 0){
	     			roleName = roleList.get(0).getName();
	     		}else{
	     			roleName = roleName + "、" + roleList.get(i).getName();
	     		}
	     	}
			model.setOldRole(roleName);		//将拼接起来的角色保存在以前的角色属性中
		}
		
		//User user = userService.getById(userId);
		positionService.save(model);	//直接调用service层，保存带数据库
		
		System.out.println("UserID:"+userId);
		return "toList";
	}

	
	
	//get, set
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getVname() {
		return Vname;
	}
	public void setVname(String vname) {
		Vname = vname;
	}
}
