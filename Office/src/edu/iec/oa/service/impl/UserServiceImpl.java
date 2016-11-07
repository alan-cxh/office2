package edu.iec.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.User;
import edu.iec.oa.service.UserService;

/**
 * @author TaoJG
 * 开事务
 * 注入到IOC容器中
 * 用户实现类
 */

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService{
	//////////////除了继承DaoSupportImpl里面已经实现的共同方法外，还有自己的方法要实现//////////////////////

	/**用户登录*/
	public User getLoginNameAndPassword(String loginName, String password) {
		//将前台传来的密码加密后再与数据库密码字段比较
		String loginPWD = DigestUtils.md5Hex(password);
		return (User) getSession()//
				.createQuery("FROM User u WHERE u.loginName=? AND password=? AND u.status=?")//
				.setParameter(0, loginName)//用户名：前台页面传来的
				.setParameter(1, loginPWD)//密码：前台页面传来的
				.setParameter(2, 1)//状态：状态要为正常
				.uniqueResult();//返回唯一结果
	}

	//判断登录名是否存在
	public User checkLoginName(String loginName) {
		return (User)getSession()
				.createQuery("FROM User u WHERE u.loginName=?")//
				.setParameter(0, loginName)//
				.uniqueResult();
	}

	//根据部门名称统计人数
	public Long countByDepartmentName(String name) {
		return (Long) getSession()
				.createQuery("SELECT COUNT (*) FROM User u WHERE u.department.name=?")//
				.setParameter(0, name)//
				.uniqueResult();
	}

	//根据民族统计人数
	public Long countByNation(String name) {
		return (Long) getSession()
				.createQuery("SELECT COUNT (*) FROM User u WHERE u.nation=?")//
				.setParameter(0, name)//
				.uniqueResult();
	}

	//根据职称统计人数
	public Long countByJobTitle(String name) {
		return (Long) getSession()
				.createQuery("SELECT COUNT (*) FROM User u WHERE u.jobTitle=?")//
				.setParameter(0, name)//
				.uniqueResult();
	}
	//根据性别统计人数
	public Long countByGender(String name) {
		return (Long) getSession()
				.createQuery("SELECT COUNT (*) FROM User u WHERE u.gender=?")//
				.setParameter(0, name)//
				.uniqueResult();
	}

	//根据部门-性别统计人数
	public Long countByDepartAndGender(String gender, String departName) {
		return (Long) getSession()
				.createQuery("SELECT COUNT (*) FROM User u WHERE u.department.name=? and u.gender=?")//
				.setParameter(0, departName)//
				.setParameter(1, gender)//
				.uniqueResult();
	}
	
	//根据职称-性别统计人数
	public Long countByJobTitletAndGender(String JobTitle, String gender) {
		return (Long) getSession()
				.createQuery("SELECT COUNT (*) FROM User u WHERE u.jobTitle=? and u.gender=?")//
				.setParameter(0, JobTitle)//
				.setParameter(1, gender)//
				.uniqueResult();
	}

	//根据部门-职称统计人数
	public Long countByDepartmentAndJobTitlet(String department, String jobTitle) {
		return (Long) getSession()
				.createQuery("SELECT COUNT (*) FROM User u WHERE u.department.name=? and u.jobTitle=?")//
				.setParameter(0, department)//
				.setParameter(1, jobTitle)//
				.uniqueResult();
	}

}
