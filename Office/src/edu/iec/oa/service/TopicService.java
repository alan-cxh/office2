package edu.iec.oa.service;

import java.util.List;

import edu.iec.oa.base.DaoSupport;
import edu.iec.oa.domain.Forum;
import edu.iec.oa.domain.PageBean;
import edu.iec.oa.domain.Topic;

/**
 * @author TaoJG
 * 论坛管理：主题
 */
public interface TopicService extends DaoSupport<Topic>{

	
	/**
	 * @return
	 * 具体某个模块里面的主题列表
	 * 排序要求：置顶帖应该在最上面，其他按最后更新的时间排序，让新状态在上面
	 */
	
	public List<Topic> findTopicByForum(Forum forum);

	//根据分页查找topic
	public PageBean getPageBean(int pageNum, Forum forum);

	//根据分页查找topic
	public PageBean getPageBeanByForum(int pageNum, int pageSize, Forum forum);

}
