package edu.iec.oa.domain;

/**
 * @author TaoJG
 * 个人通讯录：属于某个用户的私有通讯录
 */
public class PersonalAddrBook extends AddressBook{
	private static final long serialVersionUID = 1L;
	
	private User user;	//用户与通讯录的关系：一对多

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
