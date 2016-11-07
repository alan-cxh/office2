package edu.iec.oa.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.iec.oa.domain.Privilege;
import edu.iec.oa.service.PrivilegeService;

public class InitListener implements ServletContextListener{
	
	//private Log log = LogFactory.getLog(InitListener.class);

	//初始化
	public void contextInitialized(ServletContextEvent sce) {
		//获取容器里面的service
		ServletContext application = sce.getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
	    PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl");
		//在service中找到顶级菜单
		List<Privilege> topPrivilegeList =privilegeService.findTopList();
		application.setAttribute("topPrivilegeList", topPrivilegeList);
		
		//在service中找到所有URL
		List<Privilege> allPrivilegeUrl =privilegeService.getAllPrivilegeUrl();
		application.setAttribute("allPrivilegeUrl", allPrivilegeUrl);
		
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}


}
