package edu.iec.oa.service;


import edu.iec.oa.base.DaoSupport;
import edu.iec.oa.domain.ZD_bargainType;

/**
 * @author TaoJG
 */

public interface ZD_bargainTypeService extends DaoSupport<ZD_bargainType>{

	public ZD_bargainType checkName(String name);
	
	

}
