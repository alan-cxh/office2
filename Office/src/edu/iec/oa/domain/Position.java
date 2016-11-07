package edu.iec.oa.domain;

import java.util.Date;

/**
 * @author Administrator
 * 职位变更
 */
public class Position {
	
	private Long id;			//id
	private String oldRole;		//以前的职位，角色
	private String oldDepartment;//以前的部门
	
	private String newRole;		//现在职位
	private String newDepartment;//现在的部门
	private String reason;		//职位更变原因
	private Date changeTime;	//职位变更时间
	
	//用于记录，相当于日志
	private String operator;	//操作者
	private Date operatTime;	//操作时间
	private String ipAddress;	//操作者的IP
	
	private User user;			//变更的职工
	
	//get set
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOldRole() {
		return oldRole;
	}
	public void setOldRole(String oldRole) {
		this.oldRole = oldRole;
	}
	public String getNewRole() {
		return newRole;
	}
	public void setNewRole(String newRole) {
		this.newRole = newRole;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperatTime() {
		return operatTime;
	}
	public void setOperatTime(Date operatTime) {
		this.operatTime = operatTime;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getNewDepartment() {
		return newDepartment;
	}
	public void setNewDepartment(String newDepartment) {
		this.newDepartment = newDepartment;
	}
	public String getOldDepartment() {
		return oldDepartment;
	}
	public void setOldDepartment(String oldDepartment) {
		this.oldDepartment = oldDepartment;
	}
}
