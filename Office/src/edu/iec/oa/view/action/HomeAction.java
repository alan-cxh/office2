package edu.iec.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.BaseAction;
import edu.iec.oa.domain.TaskView;

/**
 * @author TaoJG
 * 注入到IOC容器中
 * 多例获取
 */

@Controller
@Scope("prototype")
public class HomeAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private int taskCount;

	/**首页*/
	public String index() throws Exception {
		return "index";
	}
	
	/**顶部*/
	public String top() throws Exception {
		//由于要显示待我审批的数量，所以准备数据
		List<TaskView> taskViewList = flowService.findMyTaskViewList(getCurrentUser());
		taskCount = taskViewList.size();
		ActionContext.getContext().getValueStack().push(taskCount);
		
		return "top";
	}
	
	/**底部*/
	public String bottom() throws Exception {
		return "bottom";
	}
	
	/**左边*/
	public String left() throws Exception {
		return "left";
	}
	
	/**右边*/
	public String right() throws Exception {
		return "right";
	}
	
	/**版本信息页面*/
	public String versionUI() throws Exception {
		return "versionUI";
	}

	public int getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}
}
