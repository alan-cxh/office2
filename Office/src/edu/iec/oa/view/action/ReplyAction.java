package edu.iec.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.Reply;
import edu.iec.oa.domain.Topic;

/**
 * @author TaoJG
 * IOC注入，多例
 * 回复action
 */
@Controller
@Scope("prototype")
public class ReplyAction extends ModelDrivenBaseAction<Reply>{
	
	private static final long serialVersionUID = 1L;
	private Long topicId;
	
	/**添加回复页面*/
	public String addUI() throws Exception {
		//准备添加页面上所需要回显的数据
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}
	
	/**添加*/
	public String add() throws Exception {
		//封装对象:title,content是从前台获取的，strut2已经封装，这里可以不用封装
		//model.setTitle(model.getTitle());
		//model.setContent(model.getContent());
		model.setTopic(topicService.getById(topicId));
		
		//当前信息,前台没用直接添加
		model.setPostTime(new Date());
		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		
		//保存到数据库
		replyService.save(model);
		return "toTopicShow";//转到回复所在的主题页面
	}
	
	/**删除回帖（回复）**/
	public String delete() throws Exception {
		//从数据库中读取原对象：里面的数据在跳转的时候需要用到
		Reply reply = replyService.getById(model.getId());
		ActionContext.getContext().put("reply", reply);
		Topic topic = reply.getTopic();
		ActionContext.getContext().put("topic", topic);
		
		//删除回帖的时候，回帖数量要减一
		replyService.delete(model.getId());
		//删除后返回到当前的topic页面
		return "toAfterDeleteTopicShow";
	}

	//====================================================//
	
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	

}
