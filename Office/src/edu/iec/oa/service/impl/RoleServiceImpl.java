package edu.iec.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.Role;
import edu.iec.oa.service.RoleService;

/**
 * @author TaoJG
 * 开事务
 * 注入到IOC容器中
 * 角色实现类
 */

@Service
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements RoleService{
	//////////////除了继承DaoSupportImpl里面已经实现的共同方法外，还有自己的方法要实现//////////////////////

}
