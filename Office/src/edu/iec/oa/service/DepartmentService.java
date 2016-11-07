package edu.iec.oa.service;

import java.util.List;

import edu.iec.oa.base.DaoSupport;
import edu.iec.oa.domain.Department;

/**
 * @author TaoJG
 * 部门Department接口
 */
public interface DepartmentService extends DaoSupport<Department>{
	/**
	 * @return
	 * 查询顶级部门列表
	 */
	public List<Department> findTopList();

	/**
	 * @param parentId：上级部门的id
	 * @return
	 * 查询子部门列表
	 */
	public List<Department> findChildren(Long parentId);


}
