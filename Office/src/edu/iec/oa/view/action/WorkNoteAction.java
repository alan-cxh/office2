package edu.iec.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 工作日志类型列表Action
 * @author TaoJG
 * 注入到IOC容器中
 * 多例获取
 */

@Controller
@Scope("prototype")
public class WorkNoteAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	/**
	 * 点击工作日志的时候分类：默认有两种，个人工作日志和公告工作日志
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		return "list";
	}
}
