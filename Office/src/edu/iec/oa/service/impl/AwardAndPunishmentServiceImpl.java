package edu.iec.oa.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.AwardAndPunishment;
import edu.iec.oa.service.AwardAndPunishmentService;

/**
 * @author TaoJG
 * IOC注入，开事务
 * 奖惩记录AwardAndPunishment的实现类
 */
@Service
@Transactional
public class AwardAndPunishmentServiceImpl extends DaoSupportImpl<AwardAndPunishment> implements AwardAndPunishmentService{

	//根据奖励惩处类型统计数量
	public Long countByType(int type) {
		return (Long) getSession()
				.createQuery("SELECT COUNT (*) FROM AwardAndPunishment a WHERE a.type=?")//
				.setParameter(0, type)//
				.uniqueResult();
	}

	//根据部门和奖励统计数量
	public Long countByDepartAndAward(String departName, int award) {
		return (Long) getSession().createQuery(
			"SELECT COUNT (*) FROM AwardAndPunishment a WHERE a.type=? AND a.userId " +
			"IN(SELECT id FROM User u WHERE u.department.name=?)")//
			.setParameter(0, award)//
			.setParameter(1, departName)//
			.uniqueResult();
	}

	//根据部门和惩处统计数量
	public Long countByDepartAndPunishment(String departName, int punishment) {
		return (Long) getSession().createQuery(
			"SELECT COUNT (*) FROM AwardAndPunishment a WHERE a.type=? AND a.userId " +
			"IN(SELECT id FROM User u WHERE u.department.name=?)")//
			.setParameter(0, punishment)//
			.setParameter(1, departName)//
			.uniqueResult();
	}
}
