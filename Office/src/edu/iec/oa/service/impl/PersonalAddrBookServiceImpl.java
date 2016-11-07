package edu.iec.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.PersonalAddrBook;
import edu.iec.oa.domain.User;
import edu.iec.oa.service.PersonalAddrBookService;

/**
 * @author TaoJG
 * 开事务
 * 注入到IOC容器中
 * PersonalAddrBook实现类
 */

@Service
@Transactional
@SuppressWarnings("unchecked")
public class PersonalAddrBookServiceImpl extends DaoSupportImpl<PersonalAddrBook> implements PersonalAddrBookService{

	//通过当前用户来查找自己创建的通讯录
	public List<PersonalAddrBook> findAllByCurrentUser(User user) {
		return getSession().createQuery("FROM PersonalAddrBook p WHERE p.user=?")//
				.setParameter(0, user)//
				.list();
	}

}
