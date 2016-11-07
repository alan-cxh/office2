package edu.iec.oa.service;

import edu.iec.oa.base.DaoSupport;
import edu.iec.oa.domain.AwardAndPunishment;

/**
 * @author TaoJG
 * 奖惩记录 ：AwardAndPunishment接口
 */
public interface AwardAndPunishmentService extends DaoSupport<AwardAndPunishment>{

	//根据奖励惩处类型统计数量
	public Long countByType(int type);

	//根据部门和奖励统计数量
	public Long countByDepartAndAward(String departName, int award);
	
	//根据部门和惩处统计数量
	public Long countByDepartAndPunishment(String departName, int punishment);

}
