package edu.iec.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.Department;
import edu.iec.oa.domain.Position;
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
public class PositionManageAction extends ModelDrivenBaseAction<Position>{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 查询
	 */
	private String Vname;
	private String VoldDepart;		//变更前的部门名称
	private String VnewDepart;		//变更后的部门名称
	
	
	/**列表 :应该先显示用户的信息*/
	public String list() throws Exception {
//		List<Position> positionList = positionService.findAll();//获取所有Position对象
//		ActionContext.getContext().put("positionList", positionList);//将Position对象 放到值栈中
		
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentTreeUtil.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
//		System.out.println("new:"+VnewDepart.trim());
//		System.out.println("old:"+VoldDepart.trim());
		
		String Vnew = "";
		String Vold = "";
		
		if(VnewDepart != null && !VnewDepart.equals("")){
			Vnew = VnewDepart.substring(VnewDepart.indexOf("┣")+1);
		}
		if(VoldDepart != null && !VoldDepart.equals("")){
			Vold = VoldDepart.substring(VoldDepart.indexOf("┣")+1);
		}
		
		
		
		//构建查询条件
		new HqlHelper(Position.class, "p")
			//过滤条件
			.addWhereCondition(Vname != null && Vname.length() > 0, "p.user.name LIKE ?", "%"+Vname+"%")//登录名
			.addWhereCondition(VoldDepart != null && VoldDepart.length() > 0, "p.oldDepartment=?", Vold)//
			.addWhereCondition(VnewDepart != null && VnewDepart.length() > 0, "p.newDepartment=?", Vnew)//
			.preparePageBean(userService, pageNum);
		
		return "list";
	}

	/**删除*/
	public String delete() throws Exception {
		positionService.delete(model.getId());
		return "toList";
	}
	
	/**详细信息页面*/
	public String showDetailUI() throws Exception {
		//获取原对象
		Position position = positionService.getById(model.getId());
		//放到栈顶：回显
		ActionContext.getContext().getValueStack().push(position);
		return "showDetailUI";
	}

	public String getVname() {
		return Vname;
	}

	public void setVname(String vname) {
		Vname = vname;
	}

	public String getVoldDepart() {
		return VoldDepart;
	}

	public void setVoldDepart(String voldDepart) {
		VoldDepart = voldDepart;
	}

	public String getVnewDepart() {
		return VnewDepart;
	}

	public void setVnewDepart(String vnewDepart) {
		VnewDepart = vnewDepart;
	}
}
