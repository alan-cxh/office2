package edu.iec.oa.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.Notice;
import edu.iec.oa.service.NoticeService;

/**
 * @author TaoJG
 * IOC注入，开事务
 * 公告Notice的实现类
 */
@Service
@Transactional
public class NoticeServiceImpl extends DaoSupportImpl<Notice> implements NoticeService{

//////////////除了继承DaoSupportImpl里面已经实现的共同方法外，还有自己的方法要实现//////////////////////

}
