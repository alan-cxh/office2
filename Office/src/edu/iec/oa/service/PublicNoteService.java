package edu.iec.oa.service;


import java.util.List;

import edu.iec.oa.base.DaoSupport;
import edu.iec.oa.domain.PublicNote;

/**
 * @author TaoJG
 * 人事管理：职位变更接口
 */

public interface PublicNoteService extends DaoSupport<PublicNote>{

	//根据ID的降序排序，在公开日志中，后发表的应该在前面
	public List<PublicNote> getAllByTimeDESC();
	
	//管理自己的公开日志
	public List<PublicNote> getAllByMy(Long authorId);
	
}
