package edu.iec.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;


import edu.iec.oa.domain.PageBean;
import edu.iec.oa.util.Configuration;
import edu.iec.oa.util.HqlHelper;


/**
 * BaseDaoImpl<T>这个父类就不用注入了，具体注入在之类的具体实现类中注入
 * 在父类中开事务，子类就可以不用开启了
 */
@Transactional
@SuppressWarnings("unchecked")
public class DaoSupportImpl<T> implements DaoSupport<T>{
	
	@Resource
	private SessionFactory sessionFactory;//从IOC容器中获取SessionFactory

	private Class<T> clazz;

	//使用反射技术得到T的真实类型
	public DaoSupportImpl(){
		//获取当前new的对象的泛型父类类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获取第一个类型参数的真实类型，也就是T的真实类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
		//System.out.println("clazz = "+clazz);
	}
	
	//获取当前可用的session封装成一个方法，子类也可以访问
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	//公共方法的save()方法实现
	public void save(T entity) {
		getSession().save(entity);
	}

	//公共方法的delete()方法实现
	public void delete(Long id) {
		Object obj = getById(id);
		if(obj != null){
			getSession().delete(obj);
		}
	}

	//公共方法的update()方法实现
	public void update(T entity) {
		getSession().update(entity);
	}

	//公共方法的getById()方法实现
	public T getById(Long id) {
		//返回某个类的某个资源
		//比如：return getSession().get(User.class,id);
		if(id == null){
			return null;
		} else {
			return (T) getSession().get(clazz,id);
		}
	}

	//公共方法的getByIds()方法实现
	public List<T> getByIds(Long[] ids) {
		if(ids == null || ids.length == 0){
			//return null;
			//return new ArrayList<T>();//返回空的集合，不要返回null
			return java.util.Collections.EMPTY_LIST;
		}else{
			return getSession()//
					.createQuery("FROM "+clazz.getSimpleName()+" WHERE id IN (:ids)")//
					.setParameterList("ids", ids)//
					.list();
		}
	}

	//公共方法的findAll()方法实现
	public List<T> findAll() {
		return getSession()//
				.createQuery("FROM " +clazz.getSimpleName())//
				.list();
	}

	//通过ID的倒序排序查询所有
	public List<T> findAllByIdByDESC() {
		return getSession()//
				.createQuery("FROM " +clazz.getSimpleName()+ " ORDER BY id DESC")//
				.list();
	}
	/**
	 * 
	 * @param pageNum
	 * @param hqlHelper
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public PageBean getPageBean(int pageNum, HqlHelper hqlHelper){
		System.out.println("----------> 使用HqlHelper的新分页方法：BaseDaoImpl.getPageBean()");
		
		List<Object> parameters = hqlHelper.getParameters();//获得参数集合
		int pageSize = Configuration.getPageSize();//从配置文件里面读取分页大小
		
		//查询总记录数
		Query countQuery = getSession().createQuery(hqlHelper.getQueryCountHql()); // 生成查询对象
		for (int i = 0; i < parameters.size(); i++) { // 设置参数
			countQuery.setParameter(i, parameters.get(i));
		}
		//查询总记录数
		int count = ((Long) countQuery.uniqueResult()).intValue();
		
		//查询一段数据字段（分页列表）
		Query listQuery = getSession().createQuery(hqlHelper.getQueryListHql()); // 生成查询对象
		for (int i = 0; i < parameters.size(); i++) { // 设置参数
			listQuery.setParameter(i, parameters.get(i));
		}
		//开始分页数据
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		List list = listQuery.list(); // 查询
		
		return new PageBean(pageNum, pageSize, count, list);
	}
}
