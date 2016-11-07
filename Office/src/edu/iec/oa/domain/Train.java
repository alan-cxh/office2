package edu.iec.oa.domain;

import java.util.Date;

/**
 * @author TaoJG
 * 培训记录
 */
public class Train {
	//基本信息
	private Long id;		//id
	
    //不通过对象的形式存在了，难得写映射文件的对应关系
    //private User user;		//对应的职工
	
	//而是直接以职工的id和姓名保存在培训记录表里面：他们的效果是一样的
	private Long userId;	//职工的id
	private String userName;	//职工的姓名
	
	//培训信息
	private Date startTime;		//开始时间
	private String content;		//培训内容
	private String department;	//培训机构，单位
	private String address;		//培训地点
	private Date endTime;		//结束时间
	private String result;		//培训结果
	
	//操作日志。用于记录，相当于日志
	private String operator;	//操作者
	private Date operatTime;	//操作时间
	private String ipAddress;	//操作者的IP
	
	//get set
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
}
