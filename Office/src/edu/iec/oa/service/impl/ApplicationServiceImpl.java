package edu.iec.oa.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.Application;
import edu.iec.oa.service.ApplicationService;

/**
 * @author TaoJG
 * IOC注入，开事务
 * Application的实现类
 */
@Service
@Transactional
public class ApplicationServiceImpl extends DaoSupportImpl<Application> implements ApplicationService{


}
