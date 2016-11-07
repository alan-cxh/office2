package edu.iec.oa.util;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.DaoSupport;
import edu.iec.oa.domain.PageBean;

/**
 * 用于辅助生成HQL语句与参数列表
 * @author TaoJG
 *
 */
public class HqlHelper {
	
	private String fromClause; 			// FROM子句
	private String whereClause = ""; 	// WHERE子句
	private String orderByClause = ""; 	// ORDER BY子句

	private List<Object> parameters = new ArrayList<Object>(); // 参数列表
	
	/**
	 * @param clazz 实体
	 * @param alias 简称
	 * 相当于 “FROM USER u”
	 */
	@SuppressWarnings("rawtypes")
	public HqlHelper(Class clazz, String alias) {
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}
	
	/**拼接Where子句（添加的多个过滤条件之间是使用AND连接的）
	 * @param condition WHERE 后面的条件
	 * @param params 
	 * @return
	 */
	public HqlHelper addWhereCondition(String condition, Object... params) {
		//如果没有条件：则接收当前条件
		if (whereClause.length() == 0) {
			whereClause = " WHERE " + condition;
		} else {//否则直接加条件
			whereClause += " AND " + condition;
		}
		//有参数
		if (params != null && params.length > 0) {
			for (Object param : params) {
				this.parameters.add(param);
			}
		}
		return this;
	}
	/**
	 * 如果第一个参数为true，则拼接WHERE子句（添加的多个过滤条件之间是使用AND连接的）
	 * @param append
	 * @param condition：一个过滤条件
	 * @param params
	 * @return
	 */
	public HqlHelper addWhereCondition(boolean append, String condition, Object... params) {
		if (append) {
			addWhereCondition(condition, params);//调用addWhereCondition(String condition, Object... params)方法
		}
		return this;
	}
	
	/**
	 * 拼接OrderBy子句
	 * @param propertyName
	 * @param isAsc ；  true:升序，false:降序
	 * @return
	 */
	public HqlHelper addOrderByProperty(String propertyName, boolean isAsc) {
		if (orderByClause.length() == 0) {//第一次拼接
			orderByClause = " ORDER BY " + propertyName + (isAsc ? " ASC" : " DESC");
		} else {
			orderByClause += ", " + propertyName + (isAsc ? " ASC" : " DESC");
		}
		return this;
	}
	/**
	 * 如果第一个参数为true，则拼接OrderBy子句
	 * @param append
	 * @param propertyName
	 * @param isAsc
	 * @return
	 */
	public HqlHelper addOrderByProperty(boolean append, String propertyName, boolean isAsc) {
		if (append) {
			addOrderByProperty(propertyName, isAsc);//条用addOrderByProperty(String propertyName, boolean isAsc)
		}
		return this;
	}
	/**
	 * 获取查询数据列表的的HQL语句
	 */
	public String getQueryListHql() {
		//拼接成一个完整的HQL语句
		return fromClause + whereClause + orderByClause;
	}
	
	/**
	 * 获取查询总记录数的的HQL语句
	 * @return
	 */
	public String getQueryCountHql() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}
	
	/**
	 * 获取参数列表
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}
	
	/**
	 * 准备PageBean对象到Struts2的栈顶
	 * @param service
	 * @param pageNum
	 */
	public void preparePageBean(DaoSupport<?> service, int pageNum){
		PageBean pageBean = service.getPageBean(pageNum, this);
		ActionContext.getContext().getValueStack().push(pageBean);
	}
}
