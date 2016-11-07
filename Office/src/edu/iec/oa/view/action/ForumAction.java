package edu.iec.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.Forum;
import edu.iec.oa.domain.PageBean;
import edu.iec.oa.domain.Topic;
import edu.iec.oa.util.HqlHelper;

/**
 * @author TaoJG
 * IOC注入，多例
 * 论坛action
 */
@Controller
@Scope("prototype")
public class ForumAction extends ModelDrivenBaseAction<Forum>{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 过滤条件
	 * 0表示全部主题，1表示只看精华帖
	 */
	private int viewType = 0;
	/**根据页面的查询条件
	 * 0表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)
	 * 1表示只按最后更新时间排序
	 * 2表示只按主题发表时间排序
	 * 3表示只按回复数量排序
	 */
	private int orderBy = 0;
	/**
	 * true表示升序， false表示降序
	 */
	private boolean asc = true;
	

	/**论坛模块列表*/
	public String list() throws Exception {
		//准备数据
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
 		return "list";
	}
	
	/**显示单个模块里面的主贴列表*/
	public String show() throws Exception {
		//准备选择的某个模块
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
//		//准备具体模块里面的主题
//		List<Topic> topicList = topicService.findTopicByForum(forum);
//		ActionContext.getContext().put("topicList", topicList);
		
		//显示单个主题的时候准备数据：准备主题里面的回复列表：这样是全部信息，每页分页
//		List<Reply> replyList = replyService.findByTopic(topic);
//		ActionContext.getContext().put("replyList", replyList);
		
		/**方法1 currentPage（pageNum）,pageSize两个需要传递的参数*/
//		PageBean pageBean = topicService.getPageBeanByForum(pageNum,pageSize, forum);
//		ActionContext.getContext().getValueStack().push(pageBean);
		
		/**方法2 currentPage（pageNum）,pageSize两个需要传递的参数 pageSize通过配置*/
		//PageBean pageBean = replyService.getPageBean(pageNum,topic);
		//ActionContext.getContext().getValueStack().push(pageBean);
		
		//构建查询条件
		HqlHelper hqlHelper = new HqlHelper(Topic.class, "t")//
			.addWhereCondition("t.forum=?", forum)//
			.addWhereCondition(viewType == 1, "t.type=?", Topic.TYPE_BEST) // 1表示只看精华帖
			.addOrderByProperty(orderBy == 1, "t.lastUpdateTime", asc) // 1表示只按最后更新时间排序
			.addOrderByProperty(orderBy == 2, "t.postTime", asc) // 2表示只按主题发表时间排序
			.addOrderByProperty(orderBy == 3, "t.replyCount", asc) // 3表示只按回复数量排序
			.addOrderByProperty(orderBy == 0, "(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false) // 0表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)
			.addOrderByProperty(orderBy == 0, "t.lastUpdateTime", false); // 0表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)
		// 查询分页信息
		PageBean pageBean = topicService.getPageBean(pageNum, hqlHelper);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "show";
	}
	
	//get and set
	public int getViewType() {
		return viewType;
	}
	public void setViewType(int viewType) {
		this.viewType = viewType;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public boolean isAsc() {
		return asc;
	}
	public void setAsc(boolean asc) {
		this.asc = asc;
	}

}
