package edu.iec.oa.service.impl;




import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.Forum;
import edu.iec.oa.service.ForumService;


/**
 * @author TaoJG
 * IOC注入，开事务
 * 论坛管理Form的实现类
 */

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements ForumService{

	
	/**
	 * 重写父类的findAll方法
	 * 通过位置的升序排序
	 */
	@Override
	public List<Forum> findAll() {
		return getSession().createQuery("FROM Forum f ORDER BY f.position ASC")//forum的排序是根据position有关：升序
				.list();
	}
	
	/**
	 * 重写父类的保存方法
	 * 保存的时候要给position一个值
	 */
	@Override
	public void save(Forum forum) {
		// 保存到数据库
		super.save(forum);
		//设置position的值，让他的值等于id：因为Id是自增长的
		forum.setPosition(forum.getId().intValue());
	}

	/**
	 * 版块上移，最上面的不能上移
	 * @param id
	 */
	public void moveUp(Long id) {
		//找到当前要上移的forum
		Forum forum = getById(id);
		//上一个forum
		Forum upForum = (Forum) getSession()
				//查找上一个：首先查找位置小于当前位置的所有forum，然后通过位置倒序，第一个就是当前的上一个forum
				.createQuery("FROM Forum f WHERE position<? ORDER BY f.position DESC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)//第一个位置开始取
				.setMaxResults(1)//只取一个结果，这样就找到了第一个forum
				.uniqueResult();
		
		//最上面的不能上移
		if(upForum == null){
			return;
		}
		
		//交换相互的position值
		int temp = forum.getPosition();
		forum.setPosition(upForum.getPosition());
		upForum.setPosition(temp);
		
		//更新到数据库,可以不写，现在对象是持久化状态
		getSession().update(forum);
		getSession().update(upForum);
	}

	/**
	 * 版块下移，最下面的不能下移
	 * @param id
	 */
	public void moveDown(Long id) {
		//找到当前要上移的forum
		Forum forum = getById(id);
		//下一个forum
		Forum downForum = (Forum) getSession()
				//查找上一个：首先查找位置打大于当前位置的所有forum，然后通过位置的升序，第一个就是当前的上一个forum
				.createQuery("FROM Forum f WHERE position>? ORDER BY f.position ASC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)//第一个位置开始取
				.setMaxResults(1)//只取一个结果，这样就找到了第一个forum
				.uniqueResult();
		
		//最下面的不能下移
		if(downForum == null){
			return;
		}
		
		//交换相互的position值
		int temp = forum.getPosition();
		forum.setPosition(downForum.getPosition());
		downForum.setPosition(temp);
		
		//更新到数据库,可以不写，现在对象是持久化状态
		getSession().update(forum);
		getSession().update(downForum);
	}

}
