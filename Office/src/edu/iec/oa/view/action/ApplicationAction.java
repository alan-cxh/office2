package edu.iec.oa.view.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.Application;
import edu.iec.oa.domain.Template;
import edu.iec.oa.util.HqlHelper;

@Controller
@Scope("prototype")
public class ApplicationAction extends ModelDrivenBaseAction<Application> {
	private static final long serialVersionUID = 1L;
	
	private InputStream inputStream; 	// 下载用的
	
	private Long templateId;	//查询条件：选择表单时的表单Id

	/** 列表 :所有申请查询*/
	public String list() throws Exception {
		//不知为什么不弄通过get,set方法获得，所以只能这样了
		String status = ServletActionContext.getRequest().getParameter("status");
		//准备分页信息
		new HqlHelper(Application.class, "a")//
			//.addWhereCondition("a.applicant=?", getCurrentUser())// 这里是所有用户的申请记录了
			.addWhereCondition((templateId != null), "a.template.id=?", templateId)//过滤条件：根据模板查找
			.addWhereCondition((status != null && status.trim().length() > 0), "a.status=?", status)//过滤条件：根据状态查找
			.addOrderByProperty("a.applyTime", false)//过滤条件：根据申请时间降序
			.preparePageBean(applicationService, pageNum); //
		
		
		//页面上的准备数据:查找所有模板
		List<Template> templateList = templateService.findAll();
		ActionContext.getContext().put("templateList", templateList);
		
		return "list";
	}

	/**删除 */
	public String delete() throws Exception {
		applicationService.delete(model.getId());
		return "toList";
	}

	/** 申请的附件下载 */
	public String download() throws Exception {
		// 获取模板对象的信息
		Application application = applicationService.getById(model.getId());
		ActionContext.getContext().put("application", application);
		String path = application.getPath();

		// 准备默认的文件名：这里的文件名不是文件的真实名称，而是模板名称
		String fileName = application.getTitle();
		fileName = URLEncoder.encode(fileName, "utf-8"); // 解决下载的文件名的乱码问题
		ActionContext.getContext().put("fileName", fileName);//放在值栈中，通过strut2配置文件去名称

		// 准备要下载的数据
		inputStream = new FileInputStream(path);
		return "download";
	}
	/**在线查看word*/
	public String showOnline() throws Exception {
		Application application = applicationService.getById(model.getId());
		String path = application.getPath();
		if(path != null && !path.equals("")){
			ServletActionContext.getRequest().setAttribute("path", path);
			return "showOnline";
		}else{
			return "notFondFile";
		}
	}

	
	// =========================
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

}
