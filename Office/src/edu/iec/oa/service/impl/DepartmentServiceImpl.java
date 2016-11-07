package edu.iec.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.Department;
import edu.iec.oa.service.DepartmentService;

/**
 * @author TaoJG
 * 开事务
 * 注入到IOC容器中
 * 部门实现类
 */

@Service
@Transactional
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements DepartmentService{
	//////////////除了继承DaoSupportImpl里面已经实现的共同方法外，还有自己的方法要实现//////////////////////
	
	/**查找顶级部门*/
	public List<Department> findTopList() {
		return getSession()//查找顶级部门,条件：上级部门为空
				.createQuery("FROM Department d WHERE d.parent IS NULL")//
				.list();
	}

	/**查找子部门*/
	public List<Department> findChildren(Long parentId) {
		return getSession()//查找子部门，根据上级部门的Id查找
				.createQuery("FROM Department d WHERE d.parent.id=?")//
				.setParameter(0, parentId)//
				.list();
	}
}
