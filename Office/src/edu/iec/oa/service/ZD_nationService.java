package edu.iec.oa.service;


import edu.iec.oa.base.DaoSupport;
import edu.iec.oa.domain.ZD_nation;

/**
 * @author TaoJG
 */

public interface ZD_nationService extends DaoSupport<ZD_nation>{

	public ZD_nation checkName(String name);
	
}
