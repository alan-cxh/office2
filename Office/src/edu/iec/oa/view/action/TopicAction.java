package edu.iec.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.Forum;
import edu.iec.oa.domain.PageBean;
import edu.iec.oa.domain.Reply;
import edu.iec.oa.domain.Topic;
import edu.iec.oa.util.HqlHelper;

/**
 * @author TaoJG
 * IOC注入，多例
 * 主贴action
 */
@Controller
@Scope("prototype")
@SuppressWarnings("static-access")
public class TopicAction extends ModelDrivenBaseAction<Topic>{
	private static final long serialVersionUID = 1L;
	
	private Long forumId;
	
	private int pageSize = 10;	//每页大小
	
	/**单个主贴，主贴和回复数列表*/
	public String show() throws Exception {
		//显示单个主题的时候准备数据：当前主题
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		
		//显示单个主题的时候准备数据：准备主题里面的回复列表：这样是全部信息，每页分页
//		List<Reply> replyList = replyService.findByTopic(topic);
//		ActionContext.getContext().put("replyList", replyList);
		
		/**方法1 currentPage（pageNum）,pageSize两个需要传递的参数*/
		//PageBean pageBean = replyService.getpageBeanByTopic(pageNum,pageSize,topic);
		//ActionContext.getContext().getValueStack().push(pageBean);
		
		/**方法2 currentPage（pageNum）,pageSize两个需要传递的参数 pageSize通过配置*/
//		System.out.println("===="+pageNum);
//		PageBean pageBean = replyService.getPageBean(pageNum,topic);
//		ActionContext.getContext().getValueStack().push(pageBean);
		
		// 方式三
		// 1, 构建查询条件(FREOM Reply)
		HqlHelper hqlHelper = new HqlHelper(Reply.class, "r")//
			.addWhereCondition("r.topic=?", topic)//
			.addOrderByProperty("r.postTime", true);
		
		// 2, 查询分页信息
		PageBean pageBean = replyService.getPageBean(pageNum, hqlHelper);
		ActionContext.getContext().getValueStack().push(pageBean);//放到栈顶
		return "show";
	}
	
	/**发新贴页面*/
	public String addUI() throws Exception {
		//将发表新帖的所属版块找到，用于页面回显
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}
	
	/**发新贴*/
	public String add() throws Exception {
		//封装对象
		//：表单参数传过来的，已经封装了title，content，可以不再进行封装
		//model.setTitle(model.getTitle());
		//model.setContent(model.getContent());
		model.setForum(forumService.getById(forumId));
		
		//：当前直接获取的信息
		model.setPostTime(new Date());//添加新主题的时间：当前时间
		model.setAuthor(getCurrentUser());//添加主题的人：当前用户
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());//当前请求的IP
		
		//:应该放在业务层处理的信息
	/*	model.setType(type);
		model.setLastReply(lastReply);
		model.setReplyCount(replyCount);
		model.setLastUpdateTime(lastUpdateTime);*/
		
		topicService.save(model);
		return "toShow";//转到新主题的主题和回复页面
	}
	
	/**将帖子置为置顶帖 type=2*/
	public String setTop() throws Exception {
		//从数据库中读取原对象
		Topic topic = topicService.getById(model.getId());
		//设置要修改的字段
		topic.setType(topic.TYPE_TOP);
		//保存到数据库
		topicService.update(topic);
		return "toShow";
	}
	
	/**将帖子置为精华帖 type=1*/
	public String setBest() throws Exception {
		//从数据库中读取原对象
		Topic topic = topicService.getById(model.getId());
		//设置要修改的字段
		topic.setType(topic.TYPE_BEST);
		//保存到数据库
		topicService.update(topic);
		return "toShow";
	}
	
	
	/**将帖子置为普通帖type=0*/
	public String setNormal() throws Exception {
		//从数据库中读取原对象
		Topic topic = topicService.getById(model.getId());
		//设置要修改的字段
		topic.setType(topic.TYPE_NORMAL);
		//保存到数据库
		topicService.update(topic);
		return "toShow";
	}
	
	/**删除主贴*/
	public String delete() throws Exception {
		//从数据库中读取原对象：里面的数据在跳转的时候需要用到
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		Forum forum = topic.getForum();
		ActionContext.getContext().put("forum", forum);
		
		//删除主题时维护相关字段，在service里面实现
		topicService.delete(model.getId());
		//删除主题后应该返回到他所属的版块里面的其他主题列表
		return "toForumShow";
	}
	
	
/**********************论坛里面的帖子不应该有修改，因为有回复之后，在修改主贴的话，可能回复会混乱*************************/	
//	/**编辑：修改帖子（主贴）的页面*/
//	public String updateUI() throws Exception {
//		Topic topic = topicService.getById(model.getId());
//		//将原对象放到值栈中
//		ActionContext.getContext().put("topic", topic);
//		//将原对象放到栈顶:准备回显主题的title和content
//		ActionContext.getContext().getValueStack().push(topic);
//		return "updateUI";
//	}
	
	
//	/**编辑：修改帖子（主贴）的页面*/
//	public String update() throws Exception {
//		//将原对象放到值栈中
//		Topic topic = topicService.getById(model.getId());
//		//设置要修改的内容（字段）
//		topic.setTitle(model.getTitle());
//		topic.setContent(model.getContent());
//		//更新到数据库
//		topicService.update(topic);
//		return "toShow";//修改好之后，转到当前的主题
//	}

//=========================================================//
	public Long getForumId() {
		return forumId;
	}
	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
