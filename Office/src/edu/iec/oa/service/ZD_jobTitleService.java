package edu.iec.oa.service;


import edu.iec.oa.base.DaoSupport;
import edu.iec.oa.domain.ZD_jobTitle;

/**
 * @author TaoJG
 */

public interface ZD_jobTitleService extends DaoSupport<ZD_jobTitle>{

	public ZD_jobTitle checkName(String name);
	
	

}
