package edu.iec.oa.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.ZD_bargainType;
import edu.iec.oa.service.ZD_bargainTypeService;

/**
 * @author TaoJG
 * IOC注入，开事务
 */
@Service
@Transactional
public class ZD_bargainTypeServiceImpl extends DaoSupportImpl<ZD_bargainType> implements ZD_bargainTypeService{

	public ZD_bargainType checkName(String name) {
		return (ZD_bargainType) getSession()//
				.createQuery("FROM ZD_bargainType b WHERE b.name=?")//
				.setParameter(0, name)//
				.uniqueResult();
	}


}
