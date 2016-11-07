package edu.iec.oa.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.ZD_sex;
import edu.iec.oa.service.ZD_sexService;

/**
 * @author TaoJG
 * IOC注入，开事务
 * Application的实现类
 */
@Service
@Transactional
public class ZD_sexServiceImpl extends DaoSupportImpl<ZD_sex> implements ZD_sexService{

	public ZD_sex checkName(String name) {
		return (ZD_sex) getSession()//
				.createQuery("FROM ZD_sex s WHERE s.name=?")//
				.setParameter(0, name)//
				.uniqueResult();
	}


}
