package edu.iec.oa.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.Archives;
import edu.iec.oa.service.ArchivesService;

/**
 * @author TaoJG
 * IOC注入，开事务
 * 档案Archives的实现类
 */
@Service
@Transactional
public class ArchivesServiceImpl extends DaoSupportImpl<Archives> implements ArchivesService{


}
