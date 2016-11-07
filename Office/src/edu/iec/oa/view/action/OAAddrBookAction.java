package edu.iec.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.Department;
import edu.iec.oa.domain.User;
import edu.iec.oa.util.HqlHelper;

/**
 * @author TaoJG IOC注入，多列获取 
 * 由于公共通讯录的信息是来自OA里面用户的相关通讯信息，所以传User
 */
@Controller("oAAddrBookAction")
@Scope("prototype")
public class OAAddrBookAction extends ModelDrivenBaseAction<User> {
	private static final long serialVersionUID = 1L;
	
	private Long departmentId;
	private String VloginName;	//登录名
	private String Vname;		//姓名
	private String Vphone;	//登录名

	
	//通讯录：公共通讯录里面的信息是来自OA用户
	public String list() throws Exception {
//		List<User> oaAddressBookList = userService.findAll();
//		ActionContext.getContext().put("oaAddressBookList", oaAddressBookList);
		//准备回显查询条件的数据：部门
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		
		//构建查询条件
		new HqlHelper(User.class, "u")
			//过滤条件:模糊查询
			.addWhereCondition(departmentId != null, "u.department=?", departmentService.getById(departmentId))//部门
			.addWhereCondition(VloginName != null && VloginName.length() > 0, "u.loginName LIKE ?", "%"+VloginName+"%")//登录名
			.addWhereCondition(Vname != null && Vname.length() > 0, "u.name LIKE ?", "%"+Vname+"%")//姓名
			.addWhereCondition(Vphone != null && Vphone.length() > 0, "u.phoneNumber LIKE ?", "%"+Vphone+"%")//姓名
			.addOrderByProperty("u.id", true)//排序：升序
			.preparePageBean(userService, pageNum);
		return "list";
	}
	//查看通讯录的详细信息
	public String detailInfoUI() throws Exception {
		User user = userService.getById(model.getId());
		//放到栈顶
		ActionContext.getContext().getValueStack().push(user);
		return "detailInfoUI";
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
	public String getVphone() {
		return Vphone;
	}
	public void setVphone(String vphone) {
		Vphone = vphone;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
}
