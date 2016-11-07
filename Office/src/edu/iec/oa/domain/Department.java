package edu.iec.oa.domain;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author TaoJG
 * 部门
 */

public class Department implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;			 				//部门编号
	private String name;		 				//部门名称
	private String description;  				//部门描述
	
	private Department parent;										//自己作为子部门时的上级部门：多对一
	private Set<User> users = new HashSet<User>();					//部门里面的用户 ：多对一
	private Set<Department> children = new HashSet<Department>();	//自己作为上级部门的子部门 ：一对多
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Department> getChildren() {
		return children;
	}
	public void setChildren(Set<Department> children) {
		this.children = children;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Department getParent() {
		return parent;
	}
	public void setParent(Department parent) {
		this.parent = parent;
	}

}
