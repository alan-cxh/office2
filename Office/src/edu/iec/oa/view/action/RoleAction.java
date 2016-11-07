package edu.iec.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.Privilege;
import edu.iec.oa.domain.Role;
import edu.iec.oa.util.HqlHelper;

/**
 * @author TaoJG
 * 注入到IOC容器中
 * 多例获取
 */

@Controller
@Scope("prototype")
public class RoleAction extends ModelDrivenBaseAction<Role>{
	private static final long serialVersionUID = 1L;
	
	//角色对应的权限
	private Long[] privilegeIds;
	private String Vname;

	

	/**列表*/
	public String list() throws Exception {
//		List<Role> roleList = roleService.findAll();
//		ActionContext.getContext().put("roleList", roleList);//将roleList放入值栈中，供页面显示
		
		//构建查询条件
		new HqlHelper(Role.class, "r")
			//过滤条件
			.addWhereCondition(Vname != null && Vname.length() > 0, "r.name LIKE ?", "%"+Vname+"%")//姓名
			.addOrderByProperty("r.id", true)//排序：id升序
			.preparePageBean(roleService, pageNum);
		
		return "list";
	}

	/**添加*/
	public String add() throws Exception {
		roleService.save(model);
		return "toList";
	}
	
	/**添加页面*/
	public String addUI() throws Exception {
		return "saveUI";
	}
	
	/**删除*/
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}
	
	/**修改*/
	public String update() throws Exception {
		//从数据库中过去原对象*/
		Role role = roleService.getById(model.getId());
		//设置要修改的字段内容*/
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		
		//更新到数据库中*/
		roleService.update(role);
		return "toList";
	}
	
	/**修改页面*/
	public String updateUI() throws Exception {
		//准备在修改页面回显数据*/
		Role role = roleService.getById(model.getId());
		//将数据放到栈顶，回显数据*/
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";
	}
	
	/**设置权限页面*/
	public String setPrivilegeUI() throws Exception {
		//准备在修改页面回显数据*/
		Role role = roleService.getById(model.getId());
		//将数据放到栈顶，回显数据*/
		ActionContext.getContext().getValueStack().push(role);
		
		if(role.getPrivileges() != null){
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for(Privilege privilege : role.getPrivileges()){
				privilegeIds[index++] = privilege.getId();
			}
		}
		//准备回显的权限数据
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);
		return "setPrivilegeUI";
		
	}
	
	/**设置权限*/
	public String setPrivilege() throws Exception {
		Role role = roleService.getById(model.getId());//从数据库中获取原对象
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);//设置要修改的字段内容
		role.setPrivileges(new HashSet<Privilege>(privilegeList));
		roleService.update(role);//保存到数据库中
		return "toList";
	}
	
	//get set
	public String getVname() {
		return Vname;
	}
	public void setVname(String vname) {
		Vname = vname;
	}
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
}
