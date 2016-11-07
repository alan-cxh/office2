package edu.iec.oa.domain;

import java.util.Date;

/**
 * @author TaoJG
 * 人事合同实体
 */
public class PersonBargain {
	private Long id;//编号
	
	//不通过对象的形式存在了，难得写映射文件的对应关系
    //private User user;		//对应的职工
	
	//而是直接以职工的id和姓名保存在培训记录表里面：他们的效果是一样的
	private Long userId;		//人事合同人员的id
	private String userName;	//人事合同人员的名字
	
	private String title;		//合同标题，名称
	private String type;		//合同类型：服务合同、租赁合同、赠与合同、借款合同、承包合同、委托合同、融资租赁合同、客运合同、货运合同、居间合同、技术转让合同、供热供电合同等。形式多种多样，都不定的。
	private String description;	//合同简单描述
	
	//人事合同附件：上传的附件的相关属性
	//private File bargain;					//人事合同附件
	private String bargainFileName;			//附件:上传文件名
	private String bargainContentType;		//文件类型
	private String bargainSavePath;			//文件保存的路径
	
	//操作日志。用于记录，相当于日志
	private String operator;	//操作者
	private Date operatTime;	//操作时间
	private String ipAddress;	//操作者的IP
	
	//get and set
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBargainFileName() {
		return bargainFileName;
	}
	public void setBargainFileName(String bargainFileName) {
		this.bargainFileName = bargainFileName;
	}
	public String getBargainContentType() {
		return bargainContentType;
	}
	public void setBargainContentType(String bargainContentType) {
		this.bargainContentType = bargainContentType;
	}
	public String getBargainSavePath() {
		return bargainSavePath;
	}
	public void setBargainSavePath(String bargainSavePath) {
		this.bargainSavePath = bargainSavePath;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
