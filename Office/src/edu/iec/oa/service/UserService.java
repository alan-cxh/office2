package edu.iec.oa.service;

import edu.iec.oa.base.DaoSupport;
import edu.iec.oa.domain.User;

/**
 * @author TaoJG
 * 用户User接口
 */
public interface UserService extends DaoSupport<User>{

	//==================除了继承DaoSupportImpl公共方法(增删改查)以外的方法===========//
	public User getLoginNameAndPassword(String loginName, String password);

	//判断登录名是否存在
	public User checkLoginName(String loginName);

	//根据部门统计人数
	public Long countByDepartmentName(String name);

	//根据民族统计人数
	public Long countByNation(String name);
	
	//根据职称统计人数
	public Long countByJobTitle(String name);
	
	//根据性别统计人数
	public Long countByGender(String name);

	//根据部门-性别统计人数
	public Long countByDepartAndGender(String gender, String departName);

	//根据职称-性别统计人数
	public Long countByJobTitletAndGender(String JobTitle, String gender);

	//根据部门-性别统计人数
	public Long countByDepartmentAndJobTitlet(String department, String jobTitle);

}
