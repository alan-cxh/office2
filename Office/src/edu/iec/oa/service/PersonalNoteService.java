package edu.iec.oa.service;


import java.util.List;

import edu.iec.oa.base.DaoSupport;
import edu.iec.oa.domain.PersonalNote;
import edu.iec.oa.domain.User;

/**
 * @author TaoJG
 * 个人日志
 */

public interface PersonalNoteService extends DaoSupport<PersonalNote>{

	//==================除了继承DaoSupportImpl公共方法(增删改查)以外的方法===========//
    //在个人通讯录中，查看所有个人日志的条件是当前用户自己创建的个人日志,通过用户的id穿参数
	public List<PersonalNote> findAllByCurrentUser(User user);
	
}
