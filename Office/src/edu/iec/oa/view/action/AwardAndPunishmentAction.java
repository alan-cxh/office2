package edu.iec.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.zhuozhengsoft.pageoffice.FileSaver;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.AwardAndPunishment;
import edu.iec.oa.domain.User;
import edu.iec.oa.util.HqlHelper;

/**
 * @author TaoJG
 * 注入到IOC容器中
 * 多例获取
 * 人事管理--奖惩记录管理 附件上传，多文件上传
 */
@Controller
@Scope("prototype")
public class AwardAndPunishmentAction extends ModelDrivenBaseAction<AwardAndPunishment>{
	private static final long serialVersionUID = 1L;
	
	private File upload; 					//上传的文件
	private String uploadFileName;		    //上传文件名
	private String uploadContentType;		//文件类型
	private InputStream inputStream; 		//下载用的
	
	/**
	 * 查询条件
	 */
	private String VuserName;
	private Long Vtype;
	
	/**列表*/
	public String list() throws Exception {
//		List<AwardAndPunishment> awardAndPunishmentList = awardAndPunishmentService.findAll();
//		ActionContext.getContext().put("awardAndPunishmentList", awardAndPunishmentList);
		
		
		//构造分页信息和查询条件
		new HqlHelper(AwardAndPunishment.class, "a")
			//过滤条件
			.addWhereCondition(VuserName != null && VuserName.length() > 0, "a.userName LIKE ?", "%"+VuserName+"%")//
			.addWhereCondition(Vtype != null && Vtype == 0, "a.type=?", AwardAndPunishment.TYPE_AWARD)//奖励
			.addWhereCondition(Vtype != null && Vtype == 1, "a.type=?", AwardAndPunishment.TYPE_PUNISHMENT)//惩处
			.preparePageBean(awardAndPunishmentService, pageNum);
		
		return "list";
	}
	
	/**添加 :有附件*/
	public String add() throws Exception {
		User user = userService.getById(model.getUserId());
		//封装自己的参数，前台页面不用封装
		model.setUserName(user.getName());
		model.setOperatTime(new Date());				//操作时间
		model.setOperator(getCurrentUser().getName());	//上传者
		model.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());//上传的IP：用于记录
		if(upload != null){
			// 保存新文件相关属性到数据库
			String fileType = uploadFileName.substring(uploadFileName.lastIndexOf("."));
			String path = saveHRFile(upload,fileType);	//保存文件：文件在服务器上的绝对路径：路径+UUID文件名
			model.setProofSavePath(path);
			model.setProofFileName(uploadFileName);
			model.setProofContentType(uploadContentType);
		}
		//调用service层
		awardAndPunishmentService.save(model);
		return "toList";
	}

	/**添加页面*/
	public String addUI() throws Exception {
		//准备user的数据
		List<User> userList = userService.findAll();
		//放到值栈中
		userList.remove(0);//将超级管理员除外
		ActionContext.getContext().put("userList", userList);
		return "saveUI";
	}
	

	//**修改*//*
	public String update() throws Exception {
		//从数据库中获得原对象
		AwardAndPunishment awardAndPunishment = awardAndPunishmentService.getById(model.getId());
		//修改前台数据
		awardAndPunishment.setType(model.getType());
		awardAndPunishment.setTime(model.getTime());
		awardAndPunishment.setReson(model.getReson());
		awardAndPunishment.setContent(model.getContent());
		
		awardAndPunishment.setOperatTime(new Date());				//操作时间
		awardAndPunishment.setOperator(getCurrentUser().getName());
		awardAndPunishment.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());//上传的IP：用于记录
		
		if(upload != null){
			// 删除老文件
			String path = "";
			path = awardAndPunishment.getProofSavePath();
			if(path != null && !path.equals("")){
				File file = new File(path);//获取文件路径
				if(file.exists()){//文件存在：则删除
					file.delete();
				}
			}
			// 保存新文件
			
			String fileType = uploadFileName.substring(uploadFileName.lastIndexOf("."));	// 保存新文件相关属性到数据库
			String savePath = saveHRFile(upload,fileType);				//保存文件：文件在服务器上的绝对路径：路径+UUID文件名
			awardAndPunishment.setProofSavePath(savePath);				//文件完成路径
			awardAndPunishment.setProofFileName(uploadFileName);
			awardAndPunishment.setProofContentType(uploadContentType);
		}
		//调用service层,保存到数据库
		awardAndPunishmentService.update(awardAndPunishment);
		return "toList";
	}

	//**修改页面*//*
	public String updateUI() throws Exception {
		//修改档案时：准备用户信息
		AwardAndPunishment awardAndPunishment = awardAndPunishmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(awardAndPunishment);
		return "saveUI";
	}

	
	/**删除*/
	public String delete() throws Exception {
		//删除文件
		AwardAndPunishment awardAndPunishment = awardAndPunishmentService.getById(model.getId());
		String path = "";
		path = awardAndPunishment.getProofSavePath();
		if(path != null && !path.equals("")){
			File file = new File(path);
			if(file.exists()){
				file.delete();
			}
		}
		//直接调用service:删除数据库里面的记录
		awardAndPunishmentService.delete(model.getId());
		return "toList";
	}
	//下载附件
	public String download() throws Exception {
		//获得原对象
		AwardAndPunishment awardAndPunishment = awardAndPunishmentService.getById(model.getId());
		String path = "";
		path = awardAndPunishment.getProofSavePath();
		if(path != null && !path.equals("")){
			//构造默认的下载文件名     “张三__奖惩类型+记录”
			String type = "奖励/惩处";//默认
			if(awardAndPunishment.getType() == 0){
				type = "奖励";
			}else{
				type = "惩处";
			}
			//获取文件后缀名，用于下载
			String fileType = awardAndPunishment.getProofFileName().substring(awardAndPunishment.getProofFileName().lastIndexOf("."));
			String fileName = awardAndPunishment.getUserName()+"__"+type+"记录"+fileType;
			fileName = URLEncoder.encode(fileName, "utf-8");// 解决下载的文件名的乱码问题
			ActionContext.getContext().put("fileName", fileName);//放在值栈中，通过strut2配置文件去名称
			// 准备要下载的数据
			inputStream = new FileInputStream(path);
			return "download";
		}else{
			return "notFondFile";
		}
	}
	
	//pageOffice
		/**在线查看word*/
		public String showOnline() throws Exception {
			AwardAndPunishment awardAndPunishment = awardAndPunishmentService.getById(model.getId());
			String path = awardAndPunishment.getProofSavePath();
			if(path != null && !path.equals("")){
				ServletActionContext.getRequest().setAttribute("path", path);
				return "showOnline";
			}else{
				return "notFondFile";
			}
		}
		
		/**在线编辑*/
		public String editOnline() throws Exception {
			AwardAndPunishment awardAndPunishment = awardAndPunishmentService.getById(model.getId());
			String path = awardAndPunishment.getProofSavePath();
			if(path != null && !path.equals("")){
				ServletActionContext.getRequest().setAttribute("path", path);
				ServletActionContext.getRequest().setAttribute("id", model.getId());
				return "editOnline";
			}else{
				return "notFondFile";
			}
		}
		/**在线编辑保存*/
		public String saveArchives() throws Exception {
			Long id =Long.valueOf(getRequest().getParameter("id")) ;
			AwardAndPunishment awardAndPunishment = awardAndPunishmentService.getById(id);
			String path = awardAndPunishment.getProofSavePath();
			
			FileSaver save = new FileSaver(getRequest(),getResponse());
			save.saveToFile(path);
			save.close();
			return NONE;
		}
	
	
	
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getVuserName() {
		return VuserName;
	}

	public void setVuserName(String vuserName) {
		VuserName = vuserName;
	}

	public Long getVtype() {
		return Vtype;
	}

	public void setVtype(Long vtype) {
		Vtype = vtype;
	}

}
