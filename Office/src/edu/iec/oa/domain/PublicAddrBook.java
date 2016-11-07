package edu.iec.oa.domain;

/**
 * @author TaoJG
 * 公告通讯录：每个人都可以添加
 * user属性：是谁添加的，供管理员参考，备案
 */
public class PublicAddrBook extends AddressBook{
	private static final long serialVersionUID = 1L;
	
	private String user;	  //添加人
	private String ipAddress; //添加人的IP地址

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
