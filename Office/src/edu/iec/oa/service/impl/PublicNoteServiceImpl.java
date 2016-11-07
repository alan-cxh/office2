package edu.iec.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.PublicNote;
import edu.iec.oa.service.PublicNoteService;

/**
 * @author TaoJG
 * IOC注入，开事务
 * 论坛管理topic的实现类
 */

@Service
@Transactional
@SuppressWarnings("unchecked")
public class PublicNoteServiceImpl extends DaoSupportImpl<PublicNote> implements PublicNoteService{

	//根据ID的降序排序，在公开日志中，后发表的应该在前面
	public List<PublicNote> getAllByTimeDESC(){
		return getSession()//
				.createQuery("FROM PublicNote ORDER BY time DESC")//
				.list();
	}

	//管理自己的日志列表
	public List<PublicNote> getAllByMy(Long authorId) {
		return getSession()//
				.createQuery("FROM PublicNote p WHERE p.authorId=? ORDER BY time DESC")//
				.setParameter(0, authorId)//
				.list();
	}
}
