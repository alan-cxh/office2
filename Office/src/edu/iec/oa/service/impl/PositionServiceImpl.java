package edu.iec.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.Position;
import edu.iec.oa.service.PositionService;

/**
 * @author TaoJG
 * IOC注入，开事务
 * 论坛管理topic的实现类
 */

@Service
@Transactional
public class PositionServiceImpl extends DaoSupportImpl<Position> implements PositionService{

}
