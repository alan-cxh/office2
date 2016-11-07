package edu.iec.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.Forum;

/**
 * @author TaoJG
 * 注入到IOC容器中
 * 多例获取
 * 论坛：模块管理
 */

@Controller
@Scope("prototype")
public class ForumManageAction extends ModelDrivenBaseAction<Forum>{
	private static final long serialVersionUID = 1L;
	
	/**列表*/
	public String list() throws Exception {
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}

	/**添加*/
	public String add() throws Exception {
		forumService.save(model);
		return "toList";
	}

	/**添加页面*/
	public String addUI() throws Exception {
		return "saveUI";
	}

	/**修改*/
	public String update() throws Exception {
		//从数据库中获取原对象
		Forum forum = forumService.getById(model.getId());
		//设置要修改的字段
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		//更新到数据库
		forumService.update(forum);
		return "toList";
	}

	/**修改页面*/
	public String updateUI() throws Exception {
		//从数据库中获取原对象
		Forum forum = forumService.getById(model.getId());
		//将对象的数据放到栈顶，用于在页面回显数据
		ActionContext.getContext().getValueStack().push(forum);
		
		return "saveUI";
	}

	/**删除*/
	public String delete() throws Exception {
		forumService.delete(model.getId());
		return "toList";
	}


	/**版块上移*/
	public String moveUp() throws Exception {
		forumService.moveUp(model.getId());
		return "toList";
	}


	/**版块下移*/
	public String moveDown() throws Exception {
		forumService.moveDown(model.getId());
		return "toList";
	}

}
