package edu.iec.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.Forum;
import edu.iec.oa.domain.PageBean;
import edu.iec.oa.domain.Reply;
import edu.iec.oa.domain.Topic;
import edu.iec.oa.service.ReplyService;
import edu.iec.oa.util.Configuration;

/**
 * @author TaoJG
 * IOC注入，开事务
 * 论坛管理reply的实现类
 */

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements ReplyService{

	/**
	 * 根据主题查找相应的回复列表：按发表时间升序
	 */
	public List<Reply> findByTopic(Topic topic) {
		//按时间升序，回复的越早，越排前
		return getSession().createQuery("FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//按时间升序
				.setParameter(0, topic)//
				.list();
	}

	@Override
	public void save(Reply reply) {
		//维护相关的信息
		Topic topic = reply.getTopic();
		Forum forum = topic.getForum();

		forum.setArticleCount(forum.getArticleCount() + 1);//模块的文章数加1
		//topic.setLastReply(reply);//主题的最后回复为当前回复
		topic.setReplyCount(topic.getReplyCount() + 1);//主题的回复数加1
		topic.setLastUpdateTime(reply.getPostTime());//主题的最后更新时间为当前回复的发表时间
		//更新到数据库
		getSession().update(topic);
		getSession().update(forum);
		//保存
		getSession().save(reply);
	}

	@Override
	public void delete(Long id) {
		
		//从要删除的id获得该回帖
		Reply reply = getById(id);
		//System.out.println("被删除的回复的Id为："+id);
		//从该回帖获得所属主题
		Topic topic = reply.getTopic();
		//System.out.println("回复所属主题："+topic.getTitle());
		//从该主题获得所属版块
		Forum forum = topic.getForum();
		System.out.println("主贴所属版块："+forum.getName());
		
		//维护相关字段，主题的回复数-1，版块的文章数量-1
		topic.setReplyCount(topic.getReplyCount() - 1);
		forum.setArticleCount(forum.getArticleCount() - 1);
		
		//System.out.println("对应的相关字段变化");
		//更新到数据库
		getSession().update(topic);
		getSession().update(forum);
		
		//System.out.println("回复数量："+topic.getReplyCount());
		//System.out.println("文章数量："+forum.getArticleCount());
		
		//热后再删除该回复
		getSession().delete(reply);
		
	}


	//分页信息：主题下的回复分页
	/**
	 * pageNum:当前页
	 * pageSize：每页大小
	 */
	@SuppressWarnings("rawtypes")
	public PageBean getpageBeanByTopic(int pageNum, int pageSize, Topic topic) {
		//查询列表
		List recordList = getSession()
				.createQuery("FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//按时间升序
				.setParameter(0, topic)//
				.setFirstResult((pageNum - 1) * pageSize)//（当前页-1）*pageSize
				.setMaxResults(pageSize)//每页大小
				.list();
		//查询总数量：总记录数
		Long recordCount = (Long)getSession()
				.createQuery("SELECT COUNT (*) FROM Reply r WHERE r.topic=?")//按时间升序
				.setParameter(0, topic)//
				.uniqueResult();
		
		return new PageBean(pageNum, pageSize, recordCount.intValue(), recordList);
	}
	
	
	@SuppressWarnings("rawtypes")
	public PageBean getPageBean(int pageNum, Topic topic) {
		
		int pageSize = Configuration.getPageSize();//获得分页大小

		// 查询总记录数
		int recordCount = ((Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Reply r WHERE r.topic=?")//
				.setParameter(0, topic)//
				.uniqueResult())//
				.intValue();

		// 查询一段数据列表
		List recordList = getSession().createQuery(//
				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime")//
				.setParameter(0, topic)//
				.setFirstResult((pageNum - 1) * pageSize)//
				.setMaxResults(pageSize)//
				.list();

		return new PageBean(pageNum, pageSize, recordCount, recordList);
	}
	
}
