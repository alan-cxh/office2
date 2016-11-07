package edu.iec.oa.domain;

import java.util.HashSet;
import java.util.Set;



/**
 * @author TaoJG
 * 表单模板管理
 */
public class Template {
	
	private Long id;			//编号
	private String name;		//名称
	private String processKey;	//流程定义的KEY，也就是流程定义的名称
	private String path; 		// 上传文件在服务器端存储的路径
	private Set<Application> applications = new HashSet<Application>();//申请：表单与申请的一对多
	
	
	//===================
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
	public String getProcessKey() {
		return processKey;
	}
	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Set<Application> getApplications() {
		return applications;
	}
	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}
	
}
