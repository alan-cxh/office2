package edu.iec.oa.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.ZD_nation;
import edu.iec.oa.service.ZD_nationService;

/**
 * @author TaoJG
 * IOC注入，开事务
 * Application的实现类
 */
@Service
@Transactional
public class ZD_nationServiceImpl extends DaoSupportImpl<ZD_nation> implements ZD_nationService{

	public ZD_nation checkName(String name) {
		return (ZD_nation) getSession()//
				.createQuery("FROM ZD_nation n WHERE n.name=?")//
				.setParameter(0, name)//
				.uniqueResult();
	}


}
