package edu.iec.oa.service;

import java.util.List;

import edu.iec.oa.base.DaoSupport;
import edu.iec.oa.domain.Reply;
import edu.iec.oa.domain.Topic;

/**
 * @author TaoJG
 * 论坛管理：回复
 */
public interface ReplyService extends DaoSupport<Reply>{

	/**
	 * @param topic
	 * @return
	 * 根据主题查找相应的回复列表,按发表时间升序
	 */
	public List<Reply> findByTopic(Topic topic);

	/**查询分页信息
	 * @param pageNum
	 * @param pageSize
	 * @param forum
	 * @return
	 */

	//public PageBean getPageBean(int pageNum, Topic topic);


}
