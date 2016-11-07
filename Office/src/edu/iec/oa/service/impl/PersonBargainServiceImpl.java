package edu.iec.oa.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.PersonBargain;
import edu.iec.oa.service.PersonBargainService;

/**
 * @author TaoJG
 * IOC注入，开事务
 * 人事合同PersonBargain的实现类
 */
@Service
@Transactional
public class PersonBargainServiceImpl extends DaoSupportImpl<PersonBargain> implements PersonBargainService{

}
