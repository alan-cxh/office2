package edu.iec.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.PersonalNote;
import edu.iec.oa.domain.User;
import edu.iec.oa.service.PersonalNoteService;

/**
 * @author TaoJG
 * IOC注入，开事务
 * 论坛管理topic的实现类
 */

@Service
@Transactional
@SuppressWarnings("unchecked")
public class PersonalNoteServiceImpl extends DaoSupportImpl<PersonalNote> implements PersonalNoteService{

	public List<PersonalNote> findAllByCurrentUser(User user) {
		return getSession().createQuery("FROM PersonalNote p WHERE p.author=?")//HQL语句
				.setParameter(0, user.getName())//条件是：作者姓名=当前登录的用户的姓名
				.list();//
	}

}
