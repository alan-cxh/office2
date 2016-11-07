package edu.iec.oa.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.jbpm.api.ProcessDefinition;

/**
 * @author TaoJG
 * 这里不用继承DaoSupport接口了
 * 因为ProcessDefinitionService里面的方法全是自己定义：调用JBPM里面API
 */
public interface ProcessDefinitionService {
	/**
	 * 查询所有最新版本的流程定义列表
	 * @return
	 */
	public List<ProcessDefinition> findAllLatestVersionList();

	/**
	 * 删除指定key的所有版本的流程定义
	 * @param key
	 */
	public void deleteByKey(String key);
	
	/**
	 * 部署流程定义（使用zip包的方式）
	 * @param zipFile
	 * @return 
	 */
	public void deployByZip(File zipFile);
	
	/**查看流程定义图
	 * 获取指定流程定义中的流程图片文件的输入流
	 * @param id
	 * @return
	 */
	public InputStream getImageResourceAsStreamByPdId(String id);
}
