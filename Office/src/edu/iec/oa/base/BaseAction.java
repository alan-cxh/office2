package edu.iec.oa.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iec.oa.domain.User;
import edu.iec.oa.service.ApplicationService;
import edu.iec.oa.service.ArchivesService;
import edu.iec.oa.service.AwardAndPunishmentService;
import edu.iec.oa.service.DepartmentService;
import edu.iec.oa.service.FlowService;
import edu.iec.oa.service.ForumService;
import edu.iec.oa.service.NoticeService;
import edu.iec.oa.service.PersonBargainService;
import edu.iec.oa.service.PersonalAddrBookService;
import edu.iec.oa.service.PersonalNoteService;
import edu.iec.oa.service.PositionService;
import edu.iec.oa.service.PrivilegeService;
import edu.iec.oa.service.ProcessDefinitionService;
import edu.iec.oa.service.PublicAddrBookService;
import edu.iec.oa.service.PublicNoteService;
import edu.iec.oa.service.ReplyService;
import edu.iec.oa.service.RoleService;
import edu.iec.oa.service.TemplateService;
import edu.iec.oa.service.TopicService;
import edu.iec.oa.service.TrainService;
import edu.iec.oa.service.UserService;
import edu.iec.oa.service.ZD_bargainTypeService;
import edu.iec.oa.service.ZD_jobTitleService;
import edu.iec.oa.service.ZD_nationService;
import edu.iec.oa.service.ZD_sexService;

/**BaseAction
 * @author TaoJG
 */
public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	protected String message = "";
	
	/**Service实例声明,从IOC容器中取出来*/
	@Resource
	protected RoleService roleService;//角色
	
	@Resource
	protected DepartmentService departmentService;//部门
	
	@Resource
	protected UserService userService;//用户
	
	@Resource
	protected PrivilegeService privilegeService;//权限
	
	@Resource
	protected NoticeService noticeService;//公告
	
	@Resource
	protected ForumService forumService;//论坛：版块管理

	@Resource
	protected TopicService topicService;//论坛：主题
	
	@Resource
	protected ReplyService replyService;//论坛：回复
	
	@Resource
	protected PersonalAddrBookService personalAddrBookService;//通讯录：个人通讯录
	
	@Resource
	protected PublicAddrBookService publicAddrBookService;//通讯录：公共通讯录
	
	@Resource
	protected ArchivesService archivesService;//人事资源：档案管理
	
	@Resource 
	protected PositionService positionService;//人事资源：职位变更
	
	@Resource 
	protected TrainService trainService;//人事资源：培训记录
	
	@Resource 
	protected AwardAndPunishmentService awardAndPunishmentService;//人事资源：奖惩记录
	
	@Resource 
	protected PersonBargainService personBargainService;//人事资源：人事合同
	
	@Resource 
	protected PersonalNoteService personNoteService;//个人办公：工作日志：个人日志
	
	@Resource 
	protected PublicNoteService publicNoteService;//个人办公：工作日志：公开日志
	
	@Resource
	protected ProcessDefinitionService processDefinitionService;//部署流程定义
	
	@Resource
	protected TemplateService templateService;//审批流转的表单模板
	
	@Resource
	protected FlowService flowService;//审批流转
	
	@Resource
	protected ApplicationService applicationService;//申请记录
	
	@Resource
	protected ZD_nationService zd_nationService;//字典：民族
	
	@Resource
	protected ZD_bargainTypeService zd_bargainTypeService;//字典：合同类别
	
	@Resource
	protected ZD_sexService zd_sexService;//字典：性别
	
	@Resource
	protected ZD_jobTitleService zd_jobTitleService;//字典：职称
	
	//获取当前的登录用户
	protected User getCurrentUser() {
		//从session中获取当前的用户
		User user = (User) ActionContext.getContext().getSession().get("user");
		return user;
	}
	
	/**
	 * 保存上传的文件，并返回在服务器端真实的存储路径
	 * @param upload
	 * 这是审批流转相关的文件保存路径
	 * @return
	 */
	protected String saveUploadFile(File upload) {
		//1, 得到在保存的文件路径的真实地址
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");//文件夹的命名规则：以日期命名
		String basePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/uploadFile/approveFlow");
		String datePath = sdf.format(new Date());
		
		//2, 如果文件夹不存在，就创建(/WEB-INF/uploadFile/approveFlow/yyyy/MM/dd/)
		File dir = new File(basePath + datePath);
		if(!dir.exists()){
			dir.mkdirs();//多级创建
		}
		
		//path:保存路径+uuid
		String path = basePath+ datePath + UUID.randomUUID().toString() + ".doc"; // 注意同名的问题，可以使用uuid做为文件名
		File destFile = new File(path);
		
		// 3, 移动文件
		upload.renameTo(destFile);
		return path;
	}
	/**
	 * @param upload
	 * @return
	 * 这是人事管理相关的文件保存路径
	 */
	protected String saveHRFile(File upload,String fileType) {
		//1, 得到在保存的文件路径的真实地址
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");//文件夹的命名规则：以日期命名
		String basePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/uploadFile/HR");
		String datePath = sdf.format(new Date());
		
		//2, 如果文件夹不存在，就创建(/WEB-INF/uploadFile/approveFlow/yyyy/MM/dd/)
		File dir = new File(basePath + datePath);
		if(!dir.exists()){
			dir.mkdirs();//多级创建
		}
		
		//path:保存路径+uuid:fileType 是文件后缀名,doc或者docx
		String path = basePath+ datePath + UUID.randomUUID().toString() + fileType; // 注意同名的问题，可以使用uuid做为文件名 
		File destFile = new File(path);
		
		// 3, 移动文件
		upload.renameTo(destFile);
		return path;
	}
	
	//获得HttpServletRequest对象
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	//获得Response对象
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	/**
	 * 页码
	 * pageSize
	 */
	protected int pageNum = 1;
	protected int pageSize = 10;
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
