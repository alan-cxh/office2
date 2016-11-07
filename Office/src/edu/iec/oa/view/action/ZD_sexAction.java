package edu.iec.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.ZD_sex;
/**
 * @author TaoJG
 * IOC注入，多例
 */
@Controller
@Scope("prototype")
public class ZD_sexAction extends ModelDrivenBaseAction<ZD_sex>{
	private static final long serialVersionUID = 1L;
	
	/**列表*/
	public String list() throws Exception {
		List<ZD_sex> sexList = zd_sexService.findAll();
		ActionContext.getContext().put("sexList", sexList);
		return "list";
	}

	/**添加*/
	public String add() throws Exception {
		if(zd_sexService.checkName(model.getName()) != null){
			addFieldError("name", "该记录已经存在，请重新输入");
			this.addUI();
			return "saveUI";
		}else{
			zd_sexService.save(model);
			return "toList";
		}
	}

	/**添加页面*/
	public String addUI() throws Exception {
		return "saveUI";
	}

	/**修改*/
	public String update() throws Exception {
		ZD_sex sex = zd_sexService.getById(model.getId());
		
		if(zd_sexService.checkName(model.getName()) != null){
			addFieldError("name", "该记录已经存在，请重新输入");
			this.updateUI();
			return "saveUI";
		}else{
			sex.setName(model.getName());
			zd_sexService.update(sex);
			return "toList";
		}
	}

	/**修改页面*/
	public String updateUI() throws Exception {
		ZD_sex sex = zd_sexService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(sex);
		return "saveUI";
	}

	/**删除*/
	public String delete() throws Exception {
		zd_sexService.delete(model.getId());
		return "toList";
	}

}
