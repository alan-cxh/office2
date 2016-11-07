package edu.iec.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author TaoJG
 * 角色
 */

public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;			//岗位编号
	private String name;		//岗位名称
	private String description;	//岗位描述
	private Set<User> users = new HashSet<User>();				//角色对应的用户：多对多
	private Set<Privilege> privileges = new HashSet<Privilege>();//角色对应的权限：多对多
	
	
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
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	
}
