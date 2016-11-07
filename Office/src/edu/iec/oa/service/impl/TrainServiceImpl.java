package edu.iec.oa.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.Train;
import edu.iec.oa.service.TrainService;

/**
 * @author TaoJG
 * IOC注入，开事务
 * 培训记录Train的实现类
 */
@Service
@Transactional
public class TrainServiceImpl extends DaoSupportImpl<Train> implements TrainService{


}
