package edu.iec.oa.service;


import edu.iec.oa.base.DaoSupport;
import edu.iec.oa.domain.ZD_sex;

/**
 * @author TaoJG
 */

public interface ZD_sexService extends DaoSupport<ZD_sex>{

	public ZD_sex checkName(String name);
	
	

}
