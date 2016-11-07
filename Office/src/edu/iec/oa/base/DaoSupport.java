package edu.iec.oa.base;

import java.util.List;

import edu.iec.oa.domain.PageBean;
import edu.iec.oa.util.HqlHelper;


public interface DaoSupport<T> {
	/**
	 * 添加实体
	 * 
	 * @param entity
	 */
	public void save(T entity);

	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 修改实体
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * 按ID查询
	 * 
	 * @param id
	 * @return
	 */
	public T getById(Long id);

	/**
	 * 按ID查询
	 * 
	 * @param ids
	 * @return
	 */
	public List<T> getByIds(Long[] ids);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 查询所有：通过id的倒序排序
	 * 
	 * @return
	 */
	public List<T> findAllByIdByDESC();
	
	/**
	 * 查询分页信息接口
	 * @param pageNum
	 * @param hqlHelper
	 * 包含查询用的HQL语句与参数列表
	 * @return
	 */
	PageBean getPageBean(int pageNum, HqlHelper hqlHelper);

}
