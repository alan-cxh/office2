package edu.iec.oa.view.action;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.PersonalNote;
import edu.iec.oa.util.HqlHelper;

/**
 * @author TaoJG
 * 注入到IOC容器中
 * 多例获取
 * 职位更变Action
 */

@Controller
@Scope("prototype")
public class PersonalNoteAction extends ModelDrivenBaseAction<PersonalNote>{
	private static final long serialVersionUID = 1L;
	
	private String Vtitle;//查询条件：日志名称
	
	/**列表 :应该先显示用户的信息*/
	public String list() throws Exception {
		//根据当前用户获得日志列表
//		List<PersonalNote> personalNoteList = personNoteService.findAllByCurrentUser(getCurrentUser());
//		ActionContext.getContext().put("personalNoteList", personalNoteList);//将User对象 放到值栈中
		
		//构建查询条件
		new HqlHelper(PersonalNote.class, "p")
			//过滤条件
			.addWhereCondition("p.author=?", getCurrentUser().getName())//当前用户
			.addWhereCondition(Vtitle != null && Vtitle.length() > 0, "p.title LIKE ?", "%"+Vtitle+"%")//
			.addOrderByProperty("p.title", false)//排序：时间降序
			.preparePageBean(personNoteService, pageNum);

		return "list";
	}

	/**添加页面*/
	public String addUI() throws Exception {
		//这里不需要准备数据回显，直接跳到添加页面
		return "saveUI";
	}

	/**添加*/
	public String add() throws Exception {
		//封装页面没有的参数
		model.setAuthor(getCurrentUser().getName());
		model.setTime(new Date());
		
		personNoteService.save(model);
		return "toList";
	}
	/**删除*/
	public String delete() throws Exception {
		personNoteService.delete(model.getId());//调用service的删除
		return "toList";
	}
	/**修改页面*/
	public String updateUI() throws Exception {
		//获得原对象
		PersonalNote personalNote = personNoteService.getById(model.getId());
		//放入栈顶
		ActionContext.getContext().getValueStack().push(personalNote);
		return "saveUI";
	}
	/**修改*/
	public String update() throws Exception {
		//获得原对象
		PersonalNote personalNote = personNoteService.getById(model.getId());
		//修改相应字段
		personalNote.setTitle(model.getTitle());
		personalNote.setContent(model.getContent());
		
		//更新到数据库
		personNoteService.update(personalNote);
		return "toList";
	}
	/**详细信息页面*/
	public String showDetailUI() throws Exception {
		//获得原对象
		PersonalNote personalNote = personNoteService.getById(model.getId());
		//放入栈顶
		ActionContext.getContext().getValueStack().push(personalNote);;
		return "showDetailUI";
	}

	
	
	public String getVtitle() {
		return Vtitle;
	}

	public void setVtitle(String vtitle) {
		Vtitle = vtitle;
	}
}
