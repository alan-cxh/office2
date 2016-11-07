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
import edu.iec.oa.domain.PersonBargain;
import edu.iec.oa.domain.User;
import edu.iec.oa.domain.ZD_bargainType;
import edu.iec.oa.util.HqlHelper;

/**
 * @author TaoJG
 * 注入到IOC容器中
 * 多例获取
 * 人事管理--奖惩记录管理 附件上传，多文件上传
 */

@Controller
@Scope("prototype")
public class PersonBargainAction extends ModelDrivenBaseAction<PersonBargain>{
	private static final long serialVersionUID = 1L;
	//单文件上传
	private File upload; 					//上传的文件
	private String uploadFileName;		    //上传文件名
	private String uploadContentType;		//文件类型
	private InputStream inputStream; 		//下载用的
	
	/**
	 * 查询
	 */
	private String VuserName;	//教工姓名
	private String Vtype;	//合同类型
	
	
	/**列表*/
	public String list() throws Exception {
//		List<PersonBargain> personBargainList = personBargainService.findAll();
//		ActionContext.getContext().put("personBargainList", personBargainList);
		
		List<ZD_bargainType> bargainTypeList = zd_bargainTypeService.findAll();
		ActionContext.getContext().put("bargainTypeList", bargainTypeList);
		
		new HqlHelper(PersonBargain.class, "p")
			//过滤条件
			.addWhereCondition(VuserName != null && VuserName.length() > 0, "p.userName LIKE ?", "%"+VuserName+"%")//
			.addWhereCondition(Vtype != null && Vtype.length() > 0, "p.type=?",Vtype)//
			.preparePageBean(personBargainService, pageNum);
		
		return "list";
	}
	
	/**添加 :有附件*/
	public String add() throws Exception {
		User user = userService.getById(model.getUserId());
		model.setUserName(user.getName());
		model.setUserId(model.getUserId());
		
		model.setOperatTime(new Date());				//操作时间
		model.setOperator(getCurrentUser().getName());	//上传者
		model.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());//上传的IP：用于记录
		
		if(upload != null){
			//获取文件后缀名
			String fileType = uploadFileName.substring(uploadFileName.lastIndexOf("."));
			String path = saveHRFile(upload,fileType);	//保存文件：文件在服务器上的绝对路径：路径+UUID文件名
			model.setBargainSavePath(path);				//文件保存的路径：保存在数据库中
			model.setBargainFileName(uploadFileName);	//文件名，这个文件名是上传时候的文件名
			model.setBargainContentType(uploadContentType);	//文件类型
		}
		
		//调用service层
		personBargainService.save(model);
		return "toList";
	}

	/**添加页面*/
	public String addUI() throws Exception {
		//准备user的数据
		List<User> userList = userService.findAll();
		//放到值栈中
		userList.remove(0);//将超级管理员除外
		ActionContext.getContext().put("userList", userList);
		
		List<ZD_bargainType> bargainTypeList = zd_bargainTypeService.findAll();
		ActionContext.getContext().put("bargainTypeList", bargainTypeList);
		return "saveUI";
	}
	

	//**修改*//*
	public String update() throws Exception {
		//从数据库中获得原对象
		PersonBargain personBargain = personBargainService.getById(model.getId());
		
		//页面需要修改的字段
		personBargain.setTitle(model.getTitle());
		personBargain.setType(model.getType());
		personBargain.setDescription(model.getDescription());
		
		if(upload != null){//判断是否新上传文件
			String path = "";
			path = personBargain.getBargainSavePath();
			if(path != null && !path.equals("")){
				File file = new File(path);
				if(file.exists()){//文件存在：则删除
					file.delete();
				}
			}
			
			// 保存新文件
			String fileType = uploadFileName.substring(uploadFileName.lastIndexOf("."));//获取文件后缀名
			String savePath = saveHRFile(upload,fileType);				//保存文件：文件在服务器上的绝对路径：路径+UUID文件名
			personBargain.setBargainSavePath(savePath);					//文件保存的路径：保存在数据库中
			personBargain.setBargainFileName(uploadFileName);			//文件名，这个文件名是上传时候的文件名
			personBargain.setBargainContentType(uploadContentType);		//文件类型
		}
		personBargain.setOperatTime(new Date());						//操作时间
		personBargain.setOperator(getCurrentUser().getName());			//上传者
		personBargain.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());
		//调用service层,保存到数据库
		personBargainService.update(personBargain);
		return "toList";
	}

	//**修改页面*//*
	public String updateUI() throws Exception {
		//修改档案时：准备用户信息
		
		List<ZD_bargainType> bargainTypeList = zd_bargainTypeService.findAll();
		ActionContext.getContext().put("bargainTypeList", bargainTypeList);
		
		PersonBargain personBargain = personBargainService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(personBargain);
		
		return "saveUI";
	}

	/**删除*/
	public String delete() throws Exception {
		//1.删除服务器上对应的文件
		PersonBargain personBargain = personBargainService.getById(model.getId());
		String path = "";
		path = personBargain.getBargainSavePath();
		if(path != null && !path.equals("")){
			File file = new File(path);
			if(file.exists()){
				file.delete();
			}
		}
		//2,直接调用service：删除数据库里面的记录
		personBargainService.delete(model.getId());
		return "toList";
	}
	
	//下载附件
	public String download() throws Exception {
		PersonBargain personBargain = personBargainService.getById(model.getId());
		String path = "";
		path = personBargain.getBargainSavePath();//构造下载路径
		if(path != null && !path.equals("")){
			//获取文件后缀名，用于下载
			String fileType = personBargain.getBargainFileName().substring(personBargain.getBargainFileName().lastIndexOf("."));
			
			String fileName = personBargain.getUserName()+"__"+personBargain.getTitle()+fileType;//构造下载默认文件名
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
			PersonBargain personBargain = personBargainService.getById(model.getId());
			String path = personBargain.getBargainSavePath();
			if(path != null && !path.equals("")){
				ServletActionContext.getRequest().setAttribute("path", path);
				return "showOnline";
			}else{
				return "notFondFile";
			}
		}
		
		/**在线编辑*/
		public String editOnline() throws Exception {
			PersonBargain personBargain = personBargainService.getById(model.getId());
			String path = personBargain.getBargainSavePath();
			if(path != null && !path.equals("")){
				ServletActionContext.getRequest().setAttribute("path", path);
				ServletActionContext.getRequest().setAttribute("id", model.getId());
				return "editOnline";
			}else{
				return "notFondFile";
			}
		}
		/**在线编辑保存*/
		public String savePersonBargain() throws Exception {
			Long id =Long.valueOf(getRequest().getParameter("id")) ;
			PersonBargain personBargain = personBargainService.getById(id);
			String path = personBargain.getBargainSavePath();
			
			FileSaver save = new FileSaver(getRequest(),getResponse());
			save.saveToFile(path);
			save.close();
			return NONE;
		}
	
	//================================
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

	public String getVtype() {
		return Vtype;
	}

	public void setVtype(String vtype) {
		Vtype = vtype;
	}
	
}
