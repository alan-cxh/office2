package edu.iec.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author TaoJG
 * 权限
 */

public class Privilege implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;		//权限编号
	private String url;		//权限能访问的URL地址
	private String name;	//权限名称
	private String icon;	//一级菜单的图标
	
	private Privilege parent;		//上级权限:对多一
	private Set<Privilege> children = new HashSet<Privilege>();	//下级权限：一对多
	private Set<Role> roles = new HashSet<Role>();				//权限对应的角色：多对多
	
	//无参构造方法
	public Privilege() {
		
	}
	//有参构造方法，分配权限传来的"权限名称"，"URL地址"，"上级权限（菜单）"；初始化数据要用到
	public Privilege(String name, String url, Privilege parent, String icon) {
		this.name = name;
		this.url = url;
		this.parent = parent;
		this.icon = icon;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Privilege getParent() {
		return parent;
	}
	public void setParent(Privilege parent) {
		this.parent = parent;
	}
	public Set<Privilege> getChildren() {
		return children;
	}
	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
