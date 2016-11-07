package edu.iec.oa.service.impl;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.base.DaoSupportImpl;
import edu.iec.oa.domain.Template;
import edu.iec.oa.service.TemplateService;

/**
 * @author TaoJG
 * 申请表单模板管理的实现类
 * IOC注入
 * 开事务
 */
@Service
@Transactional
public class TemplateServiceImpl extends DaoSupportImpl<Template> implements TemplateService {

	//重写DaoSupportImpl里面的delete方法，
	@Override
	public void delete(Long id) {
		// 删除数据库记录
		Template template = getById(id);
		getSession().delete(template);

		// 删除文件（服务器上的路径）
		File file = new File(template.getPath());
		if (file.exists()) {
			file.delete();
		}
	}

	//自己的方法，
	public Template findById(Long id) {
		return (Template) getSession().createQuery("FROM Template WHERE id=?")//
				.setParameter(0, id)
				.uniqueResult();
	}
	
}
