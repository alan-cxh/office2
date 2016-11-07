package edu.iec.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.zhuozhengsoft.pageoffice.FileSaver;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.Archives;
import edu.iec.oa.domain.User;
import edu.iec.oa.util.HqlHelper;

/**
 * @author Administrator
 * 注入到IOC容器中
 * 多例获取
 * 人事管理--档案管理 附件上传，多文件上传
 */
@Controller
@Scope("prototype")
public class ArchivesAction extends ModelDrivenBaseAction<Archives>{
	private static final long serialVersionUID = 1L;
	
	private File upload; 					//上传的文件
	private String uploadFileName;		    //上传文件名
	private String uploadContentType;		//文件类型
	private InputStream inputStream; 		//下载用的
	
	private Long userId;		//前台传来的用户的id
	private String VuserName;

	/**列表*/
	public String list() throws Exception {
//		List<Archives> archivesList = archivesService.findAll();
//		ActionContext.getContext().put("archivesList", archivesList);
		
		new HqlHelper(Archives.class, "a")
			//过滤条件
			.addWhereCondition(VuserName != null && VuserName.length() > 0, "a.user.name LIKE ?", "%"+VuserName+"%")//公告标题
			.preparePageBean(archivesService, pageNum);
		return "list";
	}
	

	/**添加 :有附件*/
	public String add() throws Exception {
		model.setUser(userService.getById(userId));		//档案对应的职工
		model.setAuthor(getCurrentUser().getName());	//上传者
		model.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());//上传的IP：用于记录
		
		if(upload != null){
			//获取文件后缀名
			String fileType = uploadFileName.substring(uploadFileName.lastIndexOf("."));
			String path = saveHRFile(upload,fileType);	//保存文件：文件在服务器上的绝对路径：路径+UUID文件名
			model.setArchivesSavePath(path);			//文件保存的路径：保存在数据库中
			model.setArchivesFileName(uploadFileName);	//文件名，这个文件名是上传时候的文件名
			model.setArchivesContentType(uploadContentType);	//文件类型
		}
		archivesService.save(model);//调用service层
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
		// 1，从数据库中获取原对象
		Archives archives = archivesService.getById(model.getId());
		// 2，设置要修改的属性
		archives.setTitle(model.getTitle());
		archives.setAuthor(getCurrentUser().getName());
		archives.setDescription(model.getDescription());
		archives.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());
		
		if(upload != null){
			// 删除老文件
			String path = "";
			path = archives.getArchivesSavePath();
			if(path != null && !path.equals("")){
				File file = new File(path);//获取文件路径
				if(file.exists()){//文件存在：则删除
					file.delete();
				}
			}
			// 保存新文件相关属性到数据库
			String fileType = uploadFileName.substring(uploadFileName.lastIndexOf("."));
			String savePath = saveHRFile(upload,fileType);	//保存文件：文件在服务器上的绝对路径：路径+UUID文件名
			archives.setArchivesSavePath(savePath);
			archives.setArchivesFileName(uploadFileName);
			archives.setArchivesContentType(uploadContentType);
			
		}
		
		
		// 3，更新到数据库中
		archivesService.update(archives);
		return "toList";
	}

	/**修改页面*/
	public String updateUI() throws Exception {
		//准备数据：回显
		// 1，从数据库中获取原对象
		Archives archives = archivesService.getById(model.getId());
		// 2，放在值栈中
		ActionContext.getContext().getValueStack().push(archives);
		return "saveUI";
	}

	/**删除*/
	public String delete() throws Exception {
		
		//1.删除服务器上对应的文件
		Archives archives = archivesService.getById(model.getId());
		String path = "";
		path = archives.getArchivesSavePath();
		if(path != null && !path.equals("")){
			File file = new File(path);
			if(file.exists()){
				file.delete();
			}
		}
		//2.再调用service删除数据库中的记录；步骤1和2不能反过来
		archivesService.delete(model.getId());
		return "toList";
	}
	
	//下载附件
	public String download() throws Exception {
		//判断io的返回值是否为空，为空则表示文件不存在
		Archives archives = archivesService.getById(model.getId());
		String path = "";
		path = archives.getArchivesSavePath();//构造下载路径；
		if(path != null && !path.equals("")){
			//获取文件后缀名
			String fileType = archives.getArchivesFileName().substring(archives.getArchivesFileName().lastIndexOf("."));
			//构造默认的下载文件名     “0701_张三_档案.doc”或者 “0701_张三_档案.docx”
			String fileName = archives.getUser().getLoginName()+"_"+archives.getUser().getName()+"_档案"+fileType;
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
		Archives archives = archivesService.getById(model.getId());
		String path = archives.getArchivesSavePath();
		if(path != null && !path.equals("")){
			ServletActionContext.getRequest().setAttribute("path", path);
			return "showOnline";
		}else{
			return "notFondFile";
		}
	}
	
	/**在线编辑*/
	public String editOnline() throws Exception {
		Archives archives = archivesService.getById(model.getId());
		String path = archives.getArchivesSavePath();
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
		Archives archives = archivesService.getById(id);
		String path = archives.getArchivesSavePath();
		
		FileSaver save = new FileSaver(getRequest(),getResponse());
		save.saveToFile(path);
		save.close();
		return NONE;
	}
	
	//get set
	
	public Long getUserId() {
		return userId;
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


	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getVuserName() {
		return VuserName;
	}
	public void setVuserName(String vuserName) {
		VuserName = vuserName;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
