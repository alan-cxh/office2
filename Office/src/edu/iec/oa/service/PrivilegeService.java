package edu.iec.oa.service;

import java.util.List;

import edu.iec.oa.base.DaoSupport;
import edu.iec.oa.domain.Privilege;
/**
 * @author TaoJG
 * 权限Privilege接口
 */
public interface PrivilegeService extends DaoSupport<Privilege>{
	
	//除了继承DaoSupport里面的方法以外，还有自己定义的方法

	/**找到顶级菜单*/
	public List<Privilege> findTopList();

	public List<Privilege> getAllPrivilegeUrl();

}
