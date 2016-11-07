package edu.iec.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.Department;
import edu.iec.oa.util.DepartmentTreeUtil;

/**
 * @author TaoJG
 * 注入到IOC容器中
 * 多例获取
 */

@Controller
@Scope("prototype")
public class DepartmentAction extends ModelDrivenBaseAction<Department>{
	
	private static final long serialVersionUID = 1L;
	
	private Long parentId;
	
	/**部门列表*/
	public String list() throws Exception {
		List<Department> departmentList = null;
		if(parentId == null){//部门显示是从顶级部门开始显示，树状结构分级显示
			departmentList = departmentService.findTopList();
		}else{//子部门，参数：上级部门的id
			departmentList = departmentService.findChildren(parentId);
			//有子部门的时候，将上级部门放到map里面，在页面中需要
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}
	
	/**部门添加*/
	public String add() throws Exception {
		/**上级部门的id来关联添加的子部门*/
		Department department = departmentService.getById(parentId);
		model.setParent(department);
		
		departmentService.save(model);
		return "toList";
	}
	
	/**部门添加页面*/
	public String addUI() throws Exception {
		//显示部门数据，因为添加时需要选择上级部门；首先找到顶级部门
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentTreeUtil.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}
	
	/**部门删除*/
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}
	
	/**部门修改*/
	public String update() throws Exception {
		/**从数据库中过去原对象*/
		Department department = departmentService.getById(model.getId());
		Department parent = departmentService.getById(parentId);//查找上级部门
		/**设置要修改的字段内容*/
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(parent);//修改上级部门
		
		/**保存到数据库中*/
		departmentService.update(department);
		return "toList";
	}
	
	/**部门修改页面*/
	public String updateUI() throws Exception {
		//显示部门数据，因为添加时需要选择上级部门
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		
		/**准备回显数据*/
	    Department department = departmentService.getById(model.getId());
	    /**将数据放到栈顶，回显数据*/
	    ActionContext.getContext().getValueStack().push(department);
	    //显示上级部门名称
	    if(department.getParent().getId() != null){
	    	parentId = department.getParent().getId();
	    }
		return "saveUI";
	}
	
	/**辅助属性parentId,用户添加子部门是的上级部门的Id*/
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
