package edu.iec.oa.view.action;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.Train;
import edu.iec.oa.domain.User;
import edu.iec.oa.util.HqlHelper;

/**
 * @author TaoJG
 * 注入到IOC容器中
 * 多例获取
 * 培训记录Action
 */

@Controller
@Scope("prototype")
public class TrainAction extends ModelDrivenBaseAction<Train>{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 查询条件
	 */
	
	private String Vdepartment;		//培训机构
	private String Vname;		//姓名
	
	

	/**列表*/
	public String list() throws Exception {
//		List<Train> trainList = trainService.findAll();
//		ActionContext.getContext().put("trainList", trainList);
		//构建查询条件
		new HqlHelper(Train.class, "t")
			//过滤条件
			.addWhereCondition(Vname != null && Vname.length() > 0, "t.userName LIKE ?", "%"+Vname+"%")//姓名
			.addWhereCondition(Vdepartment != null && Vdepartment.length() > 0, "t.department LIKE ?", "%"+Vdepartment+"%")//培训机构
			.preparePageBean(trainService, pageNum);
		
		
		return "list";
	}

	/**添加*/
	public String add() throws Exception {
		User user = userService.getById(model.getUserId());
		//页面上的参数不用封装
		
		//需要自己封装的属性
		model.setUserId(user.getId());
		model.setUserName(user.getName());
		model.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());
		model.setOperator(getCurrentUser().getName());
		model.setOperatTime(new Date());
		
		trainService.save(model);//直接调用service层，保存带数据库
		return "toList";
	}

	/**添加页面*/
	public String addUI() throws Exception {
		//准备所有用户的数量，供页面选择
		List<User> userList = userService.findAll();
		userList.remove(0);//将超级管理员除外
		ActionContext.getContext().put("userList", userList);
		return "saveUI";
	}

	/**修改*/
	public String update() throws Exception {
		//获取元对象
		Train train = trainService.getById(model.getId());
		//修改培训内容
		train.setStartTime(model.getStartTime());
		train.setContent(model.getContent());
		train.setDepartment(model.getDepartment());
		train.setAddress(model.getAddress());
		train.setEndTime(model.getEndTime());
		train.setResult(model.getResult());
		
		//修改的操作信息
		/*model.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());
		model.setOperator(getCurrentUser().getName());
		model.setOperatTime(new Date());*/
		
		trainService.update(train);//更新到数据库
		return "toList";
	}
	
	
	/**修改页面*/
	public String updateUI() throws Exception {
		//获取元对象,修改的时候，培训人员是不可修改的，只是修改培训人员相关的培训信息
		Train train = trainService.getById(model.getId());
		//放到栈顶，回显数据
		ActionContext.getContext().getValueStack().push(train);
		return "saveUI";
	}

	/**删除*/
	public String delete() throws Exception {
		trainService.delete(model.getId());//直接调用service层:传ID主键
		return "toList";
	}
	
	/**查看详细信息*/
	public String detailInfoUI() throws Exception {
		//显示和修改页面一样：获取原对象，准备数据
		Train train = trainService.getById(model.getId());
		//将数据放到栈顶，用于回显数据
		ActionContext.getContext().getValueStack().push(train);
		return "detailInfoUI";
	}

	
	
	//get set
	public String getVdepartment() {
		return Vdepartment;
	}

	public void setVdepartment(String vdepartment) {
		Vdepartment = vdepartment;
	}

	public String getVname() {
		return Vname;
	}

	public void setVname(String vname) {
		Vname = vname;
	}
	
	
}
