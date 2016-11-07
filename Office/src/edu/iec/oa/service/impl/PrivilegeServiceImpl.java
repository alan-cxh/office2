package edu.iec.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.Privilege;
import edu.iec.oa.service.PrivilegeService;
/**
 * @author TaoJG
 * 权限Privilege实现类
 */
@Service
@Transactional
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService{
	//////////////除了继承DaoSupportImpl里面已经实现的共同方法外，还有自己的方法要实现//////////////////////
	
	/**查询顶级菜单*/
	public List<Privilege> findTopList() {
		return getSession()
				.createQuery("FROM Privilege p WHERE p.parent IS NULL")
				.list();
	}

	/**查询所有权限的URL地址*/
	public List<Privilege> getAllPrivilegeUrl() {
		return getSession()
				.createQuery("SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")//
				.list();
	}
}
