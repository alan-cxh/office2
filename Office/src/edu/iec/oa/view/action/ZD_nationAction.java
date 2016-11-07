package edu.iec.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.ZD_nation;
import edu.iec.oa.util.HqlHelper;

/**
 * @author TaoJG
 * IOC注入，多例
 */
@Controller
@Scope("prototype")
public class ZD_nationAction extends ModelDrivenBaseAction<ZD_nation>{
	private static final long serialVersionUID = 1L;
	
	//查询条件
	private String Vname;
	
	/**列表*/
	public String list() throws Exception {
//		List<ZD_nation> nationList = zd_nationService.findAll();
//		ActionContext.getContext().put("nationList", nationList);
		
		//构建查询条件
		new HqlHelper(ZD_nation.class, "n")
			//过滤条件
			.addWhereCondition(Vname != null && Vname.length() > 0, "n.name LIKE ?", "%"+Vname+"%")//名称
			.addOrderByProperty("n.id", true)//排序：升序
			.preparePageBean(userService, pageNum);
		
		return "list";
	}

	/**添加*/
	public String add() throws Exception {
		if(zd_nationService.checkName(model.getName()) != null){
			addFieldError("name", "该记录已经存在，请重新输入");
			this.addUI();
			return "saveUI";
		}else{
			zd_nationService.save(model);
			return "toList";
		}
	}

	/**添加页面*/
	public String addUI() throws Exception {
		return "saveUI";
	}

	/**修改*/
	public String update() throws Exception {
		ZD_nation nation = zd_nationService.getById(model.getId());
		if(zd_nationService.checkName(model.getName()) != null){
			addFieldError("name", "该记录已经存在，请重新输入");
			this.updateUI();
			return "saveUI";
		}else{
			nation.setName(model.getName());
			zd_nationService.update(nation);
			return "toList";
		}
	}

	/**修改页面*/
	public String updateUI() throws Exception {
		ZD_nation nation = zd_nationService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(nation);
		return "saveUI";
	}

	/**删除*/
	public String delete() throws Exception {
		zd_nationService.delete(model.getId());
		return "toList";
	}

	public String getVname() {
		return Vname;
	}

	public void setVname(String vname) {
		Vname = vname;
	}


}
