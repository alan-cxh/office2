package edu.iec.oa.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.ZD_jobTitle;
import edu.iec.oa.service.ZD_jobTitleService;

/**
 * @author TaoJG
 * IOC注入，开事务
 * Application的实现类
 */
@Service
@Transactional
public class ZD_jobTitleServiceImpl extends DaoSupportImpl<ZD_jobTitle> implements ZD_jobTitleService{

	public ZD_jobTitle checkName(String name) {
		return (ZD_jobTitle) getSession()//
				.createQuery("FROM ZD_jobTitle j WHERE j.name=?")//
				.setParameter(0, name)//
				.uniqueResult();
	}

}
