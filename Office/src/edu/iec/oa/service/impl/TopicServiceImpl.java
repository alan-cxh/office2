package edu.iec.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.Forum;
import edu.iec.oa.domain.PageBean;
import edu.iec.oa.domain.Topic;
import edu.iec.oa.service.TopicService;
import edu.iec.oa.util.Configuration;

/**
 * @author TaoJG
 * IOC注入，开事务
 * 论坛管理topic的实现类
 */

@Service
@Transactional
@SuppressWarnings("unchecked")
public class TopicServiceImpl extends DaoSupportImpl<Topic> implements TopicService{

	/**
	 * 具体某个模块里面的主题列表
	 * 排序要求：置顶帖应该在最上面，其他按最后更新的时间排序，让新状态在上面
	 */
	public List<Topic> findTopicByForum(Forum forum) {
		// 排序
		return getSession().createQuery(//按置顶帖和最后回复时间排序
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.list();
	}

	@Override
	public void save(Topic topic) {
		//设置属性并保存
		topic.setType(Topic.TYPE_NORMAL);//默认为不同贴
		//topic.setLastReply(null);//刚发表的主贴：最后回复为空
		topic.setReplyCount(0);//刚发表的主贴：回复数为0
		topic.setLastUpdateTime(topic.getPostTime());//刚发表的主贴：最后更新时间为当前帖子大发表时间
		getSession().save(topic);
		
		//维护相关的特殊属性:发表一篇新的主贴会引起版块的一些属性的改变
		Forum forum = topic.getForum();
		
		//forum.setLastTopic(topic);//对应模块的最后主题为当前主题
		forum.setTopicCount(forum.getTopicCount() + 1);//对应模块的主题加1
		forum.setArticleCount(forum.getArticleCount() + 1);//对应模块的总文章数+1；（主题加回复）
		
		getSession().update(forum);
	}

	@Override
	public void delete(Long id) {
		//获得要删除的主题
		Topic topic = getById(id);
		Forum forum = topic.getForum();
		
		//修改相关字段:删除一个主题，版块的主题数-1
		forum.setTopicCount(forum.getTopicCount()-1);
		
		//修改相关字段:删除一个主题，版块的文章数-（该主题里面的回复数+本身数量1）
		int replycount = topic.getReplyCount();
		forum.setArticleCount(forum.getArticleCount() - replycount - 1);
		//更新到数据库
		getSession().update(forum);
		//删除
		getSession().delete(topic);
	}

	
	//根据分页信息显示列表
	public PageBean getPageBeanByForum(int pageNum,int pageSize, Forum forum) {
		// 查询总记录数
		Long recordCount = (Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Topic t WHERE t.forum=?")//
				.setParameter(0, forum)//
				.uniqueResult();

		// 查询一段数据列表
		List<Topic> recordList = getSession().createQuery(//
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.setFirstResult((pageNum - 1) * pageSize)//
				.setMaxResults(pageSize)//
				.list();
		
		return new PageBean(pageNum, pageSize, recordCount.intValue(), recordList);
	}

	@SuppressWarnings("rawtypes")
	public PageBean getPageBean(int pageNum, Forum forum) {
		int pageSize = Configuration.getPageSize();
		Long recordCount = (Long) getSession().createQuery(//
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.uniqueResult();

		// 查询一段数据列表
		List recordList = getSession().createQuery(//
				"SELECT COUNT(*) FROM Topic t WHERE t.forum=?")//
				.setParameter(0, forum)//
				.setFirstResult((pageNum - 1) * pageSize)//
				.setMaxResults(pageSize)//
				.list();
		
		return new PageBean(pageNum, pageSize, recordCount.intValue(), recordList);
	}
}
