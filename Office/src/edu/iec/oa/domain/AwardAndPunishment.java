package edu.iec.oa.domain;

import java.util.Date;

/**
 * @author TaoJG
 * 认识管理：奖惩记录
 */
public class AwardAndPunishment {
	private Long id;			//id
	
	//不通过对象的形式存在了，难得写映射文件的对应关系
    //private User user;		//对应的职工
	
	//而是直接以职工的id和姓名保存在培训记录表里面：他们的效果是一样的
	private Long userId;		//奖、惩人员的id
	private String userName;	//奖、惩人员的名字
	
	//奖惩信息
	private int type;			//奖、惩类型：0代表奖励，1代表惩处
	private Date time;        	//奖、惩时间
	private String content;	    //奖、惩内容
	private String reson;		//奖、惩原因
	
	//奖励凭证：上传的附件的相关属性
	//private File proof;					//奖、惩凭证：附件
	private String proofFileName;			//附件:上传文件名
	private String proofContentType;		//文件类型
	private String proofSavePath;			//文件保存的路径
	
	//操作日志。用于记录，相当于日志
	private String operator;	//操作者
	private Date operatTime;	//操作时间
	private String ipAddress;	//操作者的IP
	
	public static final int TYPE_AWARD = 0;				//奖励
	public static final int TYPE_PUNISHMENT = 1;		//惩处
	
	
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReson() {
		return reson;
	}
	public void setReson(String reson) {
		this.reson = reson;
	}
	public String getProofFileName() {
		return proofFileName;
	}
	public void setProofFileName(String proofFileName) {
		this.proofFileName = proofFileName;
	}
	public String getProofContentType() {
		return proofContentType;
	}
	public void setProofContentType(String proofContentType) {
		this.proofContentType = proofContentType;
	}
	public String getProofSavePath() {
		return proofSavePath;
	}
	public void setProofSavePath(String proofSavePath) {
		this.proofSavePath = proofSavePath;
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
