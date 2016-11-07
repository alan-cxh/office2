package edu.iec.oa.service;

import java.util.List;

import edu.iec.oa.base.DaoSupport;
import edu.iec.oa.domain.PersonalAddrBook;
import edu.iec.oa.domain.User;

/**
 * @author TaoJG
 * 用户PersonalAddrBook接口
 */
public interface PersonalAddrBookService extends DaoSupport<PersonalAddrBook>{

	//==================除了继承DaoSupportImpl公共方法(增删改查)以外的方法===========//
	//在个人通讯录中，查看所有通讯录的条件是当前用户自己创建的通讯录,通过用户的id穿参数
	public List<PersonalAddrBook> findAllByCurrentUser(User user);
		

}
