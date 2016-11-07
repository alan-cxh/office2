package edu.iec.oa.service;

import edu.iec.oa.base.DaoSupport;
import edu.iec.oa.domain.Forum;

/**
 * @author TaoJG
 * 论坛管理Form接口
 */
public interface ForumService extends DaoSupport<Forum>{

	/**
	 * 版块上移，最上面的不能上移
	 * @param id
	 */
	public void moveUp(Long id);

	/**
	 * 版块下移，最下面的不能下移
	 * @param id
	 */
	public void moveDown(Long id);



}
