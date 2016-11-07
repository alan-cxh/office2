package edu.iec.oa.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author TaoJG
 * 用户
 */
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**用户状态：已停用*/
	public static final int STATUS_INVALID = 0;
	/**用户状态：正常*/
	public static final int STATUS_NORMAL = 1;
	
	private Long id;			//用户编号
	private String name;		//用户姓名
	private String loginName;	//登录用户名
	private String password;	//密码
	private String gender;		//性别
	private String nation;		//民族
	private String jobTitle;	//职称
	
	private Date birthday;		//出生日期
	private String phoneNumber;	//电话号码
	private String email;		//电子邮件
	private String QQ;			//QQ账号
	private String address;		//家庭住址
	private String description;	//描述
	private int status;			//用户状态，
	
	private Department department;					//用户所在的部门：多对一
	private Set<Role> roles = new HashSet<Role>();	//用户扮演的角色：一对多
	private Set<Notice> notices = new HashSet<Notice>();//用户发的公告：一对多
	private Set<PersonalAddrBook> personalAddrBooks = new HashSet<PersonalAddrBook>();//用户的个人通讯录：一对多
	private Set<Archives> archives = new HashSet<Archives>();//用户在人事管理的档案：一对多
	private Set<Position> positions = new HashSet<Position>();//用户在人事管理的职位更变：一对多
	
	
	
	/**
	 * 通过登录名判断有哪些权限
	 * @return
	 */
	public boolean hasPrivilegeByName(String name){
		if("admin".equals(loginName)){//超级管理员不用判断权限
			return true;
		}
		//普通用户要进行权限判断,从用户的角色中得到角色的权限，判断权限是否相等
		for(Role role:roles){
			for(Privilege privilege:role.getPrivileges()){
				if(privilege.getName().equals(name)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 通过登录名判断有指定的URL权限
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String priUrl){
		if("admin".equals(loginName)){//超级管理员不用判断权限
			return true;
		}
		
		//去掉后面的参数
	    int pos = priUrl.indexOf("?");
	    if( pos > -1 ){
	    	priUrl = priUrl.substring(0,pos);
	    }
	    //去掉UI后缀
	    if(priUrl.endsWith("UI")){
	    	priUrl = priUrl.substring(0, priUrl.length() - 2);
	    }
		
		@SuppressWarnings("unchecked")
		Collection<String> allPrivilegeUrl = (Collection<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrl");
	    //如果该URL不需要控制，则登录用户就可以用，比如登录，注销功能，还有显示主页面功能
	    if(!allPrivilegeUrl.contains(priUrl)){
	    	return true;
	    }
	    
		//普通用户要进行权限判断,从用户的角色中得到角色的权限，判断权限是否相等
		for(Role role:roles){
			for(Privilege privilege:role.getPrivileges()){
				if(priUrl.equals(privilege.getUrl())){
					return true;
				}
			}
		}
		return false;
	}
	
	///////////////get()，set()方法/////////////////////
	
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Set<Notice> getNotices() {
		return notices;
	}
	public void setNotices(Set<Notice> notices) {
		this.notices = notices;
	}
	public Set<PersonalAddrBook> getPersonalAddrBooks() {
		return personalAddrBooks;
	}
	public void setPersonalAddrBooks(Set<PersonalAddrBook> personalAddrBooks) {
		this.personalAddrBooks = personalAddrBooks;
	}

	public Set<Archives> getArchives() {
		return archives;
	}

	public void setArchives(Set<Archives> archives) {
		this.archives = archives;
	}

	public Set<Position> getPositions() {
		return positions;
	}

	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

}
