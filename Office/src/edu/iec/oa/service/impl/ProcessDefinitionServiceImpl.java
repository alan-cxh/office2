package edu.iec.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.service.ProcessDefinitionService;

/**
 * 流程定义的实现类
 * 
 * @author TaoJG 注入IOC容器 开事务
 */
@Service
@Transactional
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {

	@Resource
	private ProcessEngine processEngine;// 在IOC容器中获得流程引擎

	//部署流程定义（使用zip包的方式）
	public void deployByZip(File zipFile) {
		ZipInputStream zipInputStream = null;
		try {
			//准备部署的zip文件
			zipInputStream = new ZipInputStream(new FileInputStream(zipFile));
			//开始部署
			processEngine.getRepositoryService()//从引擎中获得与流程定义有关的API
			.createDeployment()//创建部署
			.addResourcesFromZipInputStream(zipInputStream)//部署文件
			.deploy();//部署
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{//关闭输入流
			if(zipInputStream != null){
				try {
					zipInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	// 删除指定部署的key的所有版本的流程定义
	public void deleteByKey(String key) {
		
		// 1：查询指定key的所有版本的流程定义
		List<ProcessDefinition> list = processEngine.getRepositoryService()//从引擎中获得与流程定义有关的API
			.createProcessDefinitionQuery()//创建流程定义查询
			.processDefinitionKey(key)//过滤条件：根据key查询
			.list();//得到集合
		// 2：循环刪除
		for(ProcessDefinition pd : list){
			processEngine.getRepositoryService().deleteDeploymentCascade(pd.getDeploymentId());//根据id循环删除
		}
		
	}

	// 查询所有最新版本的流程定义列表
	public List<ProcessDefinition> findAllLatestVersionList() {
		// 1：查询所有的流程定义（包含所有的版本），按版本升序排列。
		List<ProcessDefinition> all = processEngine.getRepositoryService()//从引擎中获得与流程定义有关的API
			.createProcessDefinitionQuery()//创建流程定义查询
			.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION)//过滤条件：根据流程定义的版本升序
			.list();//获得集合
		// 2：过滤出所有最新的版本的流程定义列表（一个key只有一个最新的版本）
		Map<String, ProcessDefinition> map = new LinkedHashMap<String, ProcessDefinition>();
		for(ProcessDefinition pd : all){
			map.put(pd.getKey(), pd);
		}
		
		return new ArrayList<ProcessDefinition>(map.values());
	}


	//查看流程定义图 获取指定流程定义中的流程图片文件的输入流
	public InputStream getImageResourceAsStreamByPdId(String pdId) {
		//1：获得信息
		ProcessDefinition pd = processEngine.getRepositoryService()//从引擎中获得与流程定义有关的API
			.createProcessDefinitionQuery()//创建流程定义查询
			.processDefinitionId(pdId)//过滤条件：根据流程定义的Id
			.uniqueResult();//返回唯一值
		
		String deploymentId =  pd.getDeploymentId();//获得部署流程定义的id
		String resourceName = pd.getImageResourceName();//获得部署的文件的图片名
		
		// 2：得到输入流
		return processEngine.getRepositoryService().getResourceAsStream(deploymentId, resourceName);
	}

}
