package edu.iec.oa.util;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.domain.Privilege;
import edu.iec.oa.domain.User;

/**
 * @author TaoJG
 * 用于初始化数据
 * 注入到IOC容器中
 */
@Component
public class InstallUtil {
	
	@Resource
	SessionFactory sessionFactory;
	
	//初始化数据方法,执行安装
	@Transactional
	@SuppressWarnings("unused")
	
	public void install(){
		Session session = sessionFactory.getCurrentSession();
		/***两个超级管理员***/
		//**********************初始化admin管理员***********************/
		User admin = new User();
		admin.setLoginName("admin");
		admin.setName("admin管理员");
		admin.setStatus(1);
		String password = DigestUtils.md5Hex("admin");//对密码MD5加密
		admin.setPassword(password);
		session.save(admin);
		
		//**********************初始化root管理员***********************/
		User root = new User();
		root.setLoginName("root");
		root.setName("root管理员");
		root.setStatus(1);
		String password1 = DigestUtils.md5Hex("root");//对密码MD5加密
		root.setPassword(password1);
		session.save(root);
		
		//**********************保存权限数据*************************/
		Privilege menu0, menu1,menu2,menu3,menu4,menu5,meun6,meun7;
		
		//**顶级菜单：系统管理**/
		menu0 = new Privilege("个人办公", null, null,"myOffice.gif");
		
		//**系统管理下的二级菜单**/
		menu1 = new Privilege("通讯录", "/addressBook_list", menu0,null);
		menu2 = new Privilege("我的任务", "/flow_myTaskList", menu0, null);
		menu3 = new Privilege("工作日记", "/workNote_list", menu0,null);
//		menu4 = new Privilege("日程安排", "/schedule_list", menu0,null);
		session.save(menu0);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);

		//**顶级菜单：审批流转**/
		menu0 = new Privilege("公文流转", null, null,"approve.gif");

		//**审批流转下的二级菜单**/
		menu1 = new Privilege("起草申请", "/flow_templateList", menu0, null);
		menu2 = new Privilege("待我审批", "/flow_myTaskList", menu0, null);
		menu3 = new Privilege("我的申请查询", "/flow_myApplicationList", menu0, null);
		session.save(menu0);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		
		//**顶级菜单：人事资源**/
		menu0 = new Privilege("人力资源", null, null,"HR.gif");
		//**人事资源下的二级菜单**/
		menu1 = new Privilege("档案管理", "/archives_list", menu0, null);
		menu2 = new Privilege("职位变更", "/position_list", menu0, null);
		menu3 = new Privilege("人事合同", "/personBargain_list", menu0, null);
		menu4 = new Privilege("培训记录", "/train_list", menu0, null);
		menu5 = new Privilege("奖惩记录", "/awardAndPunishment_list", menu0, null);
		session.save(menu0);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		//**二级菜单下的"档案管理"相关操作**/
//		session.save(new Privilege("档案列表", "/archives_list", menu1,null));
//		session.save(new Privilege("档案添加", "/archives_add", menu1,null));
//		session.save(new Privilege("档案删除", "/archives_delete", menu1,null));
//		session.save(new Privilege("档案修改", "/archives_update", menu1,null));
//		//**二级菜单下的"职位更变"相关操作**/
//		session.save(new Privilege("职位更变列表", "/position_list", menu2,null));
//		session.save(new Privilege("职位更变添加", "/position_add", menu2,null));
//		session.save(new Privilege("职位更变删除", "/position_delete", menu2,null));
//		session.save(new Privilege("职位更变修改", "/position_update", menu2,null));
//		//**二级菜单下的"人事合同"相关操作**/
//		session.save(new Privilege("人事合同列表", "/personBargain_list", menu3,null));
//		session.save(new Privilege("人事合同添加", "/personBargain_add", menu3,null));
//		session.save(new Privilege("人事合同删除", "/personBargain_delete", menu3,null));
//		session.save(new Privilege("人事合同修改", "/personBargain_update", menu3,null));
//		//**二级菜单下的"培训记录"相关操作**/
//		session.save(new Privilege("培训记录列表", "/train_list", menu4,null));
//		session.save(new Privilege("培训记录添加", "/train_add", menu4,null));
//		session.save(new Privilege("培训记录删除", "/train_delete", menu4,null));
//		session.save(new Privilege("培训记录修改", "/train_update", menu4,null));
//		//**二级菜单下的"奖惩记录"相关操作**/
//		session.save(new Privilege("奖惩记录列表", "/awardAndPunishment_list", menu5,null));
//		session.save(new Privilege("奖惩记录添加", "/awardAndPunishment_add", menu5,null));
//		session.save(new Privilege("奖惩记录删除", "/awardAndPunishment_delete", menu5,null));
//		session.save(new Privilege("奖惩记录修改", "/awardAndPunishment_update", menu5,null));
		
		
		//**顶级菜单：系统管理**/
		menu0 = new Privilege("系统管理", null, null,"system.gif");
		//**系统管理下的二级菜单**/
		menu1 = new Privilege("角色管理", "/role_list", menu0,null);
		menu2 = new Privilege("部门管理", "/department_list", menu0,null);
		menu3 = new Privilege("用户管理", "/user_list", menu0,null);
		menu4 = new Privilege("审批流程管理", "/processDefinition_list", menu0,null);
		menu5 = new Privilege("表单模板管理", "/template_list",menu0,null);
		meun6 = new Privilege("申请记录管理", "/application_list", menu0, null);
		meun7 = new Privilege("职位变更管理", "/positionManage_list", menu0, null);
		
		session.save(menu0);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		session.save(meun6);
		
		//**二级菜单"角色管理"相关操作**/
		session.save(new Privilege("角色列表", "/role_list", menu1,null));
		session.save(new Privilege("角色添加", "/role_add", menu1,null));
		session.save(new Privilege("角色删除", "/role_delete", menu1,null));
		session.save(new Privilege("角色修改", "/role_update", menu1,null));
		session.save(new Privilege("设置权限", "/role_setPrivilege", menu1,null));
		
		//**二级菜单"部门管理"相关操作**/
		session.save(new Privilege("部门列表", "/department_list", menu2,null));
		session.save(new Privilege("部门添加", "/department_add", menu2,null));
		session.save(new Privilege("部门删除", "/department_delete", menu2,null));
		session.save(new Privilege("部门修改", "/department_update", menu2,null));
		
		//**二级菜单"用户管理"相关操作**/
		session.save(new Privilege("用户列表", "/user_list", menu3,null));
		session.save(new Privilege("用户添加", "/user_add", menu3,null));
		session.save(new Privilege("用户删除", "/user_delete", menu3,null));
		session.save(new Privilege("用户修改", "/user_update", menu3,null));
		session.save(new Privilege("初始化密码", "/user_initPassword", menu3,null));
		
		//**二级菜单"职位变更管理"相关操作**/
//		session.save(new Privilege("职位变更列表", "/positionManage_list", meun6, null));
//		session.save(new Privilege("职位变更删除", "/positionManage_delete", meun6, null));
		
		//**顶级菜单：系统管理**/
		menu0 = new Privilege("字典管理", null, null,"zidian.gif");
		//**系统管理下的二级菜单**/
		menu1 = new Privilege("性别字典", "/ZDsex_list", menu0,null);
		menu2 = new Privilege("民族字典", "/ZDnation_list", menu0,null);
		menu3 = new Privilege("职称字典", "/ZDjobTitle_list", menu0,null);
		menu4 = new Privilege("合同类型字典", "/ZDbargainType_list", menu0,null);
		session.save(menu0);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		
		//**顶级菜单：数据统计**/
		menu0 = new Privilege("数据统计", null, null,"data.gif");
		//**数据统计下的二级菜单**/
		menu1 = new Privilege("师资力量", "/teacherResource_show", menu0,null);
		menu2 = new Privilege("人事统计", "/hummanResource_show", menu0,null);
		session.save(menu0);
		session.save(menu1);
		session.save(menu2);
		
		
		
		//**顶级菜单：网上交流**/
		menu0 = new Privilege("网上交流", null, null,"netConmunicate.gif");
		//**网上交流下的二级菜单**/
		menu1 = new Privilege("论坛", "/forum_list", menu0,null);
		menu2 = new Privilege("论坛管理", "/forumManage_list", menu0,null);
		session.save(menu0);
		session.save(menu1);
		session.save(menu2);
		//**三级菜单:论坛：主题管理
//		session.save(new Privilege("论坛显示","/forum_show",menu1,null));
//		session.save(new Privilege("主题显示","/topic_show",menu1,null));
//		session.save(new Privilege("主题列表","/topic_list",menu1,null));
//		session.save(new Privilege("主题添加","/topic_add",menu1,null));
		
		session.save(new Privilege("主题删除","/topic_delete",menu1,null));
		session.save(new Privilege("主题设置置顶","/topic_setTop",menu1,null));
		session.save(new Privilege("主题设置精华","/topic_setBest",menu1,null));
		session.save(new Privilege("主题设置普通","/topic_setNormal",menu1,null));
		
		//**三级菜单:论坛：回复管理
//		session.save(new Privilege("回复显示","/reply_show",menu1,null));
//		session.save(new Privilege("回复列表","/reply_list",menu1,null));
//		session.save(new Privilege("回复添加","/reply_add",menu1,null));
		session.save(new Privilege("回复删除","/reply_delete",menu1,null));
		
		//**三级菜单:论坛：模块管理
//		session.save(new Privilege("论坛模块列表","/forumManage_list",menu2,null));
//		session.save(new Privilege("论坛模块添加","/forumManage_add",menu2,null));
//		session.save(new Privilege("论坛模块删除","/forumManage_delete",menu2,null));
//		session.save(new Privilege("论坛模块修改","/forumManage_update",menu2,null));
		
		//**顶级菜单：个人设置**/
		menu0 = new Privilege("个人设置", null, null,"personalSetting.png");
		//**个人设置下的二级菜单**//
		menu1 = new Privilege("查看个人信息", "/user_seeMyInfo", menu0,null); 
		menu2 = new Privilege("维护个人信息", "/user_updateMyInfoUI", menu0,null);
		menu3 = new Privilege("登录密码修改", "/user_updateMyPWDUI", menu0,null); 
		session.save(menu0);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		
		//**顶级菜单：温馨提示**//
		menu0 = new Privilege("温馨提示", null, null,"notice.gif");
		//**温馨提示下的二级菜单**//
		menu1 = new Privilege("公告栏", "/notice_noticeUI", menu0,null); 
		menu2 = new Privilege("公告管理", "/notice_list", menu0,null); 
		session.save(menu0);
		session.save(menu1);
		session.save(menu2);
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		InstallUtil installUtil = (InstallUtil) ac.getBean("installUtil");
		installUtil.install();
		System.out.println("权限数据插入成功!");
	}
}
