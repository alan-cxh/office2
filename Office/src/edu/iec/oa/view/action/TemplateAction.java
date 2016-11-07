package edu.iec.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.opensymphony.xwork2.ActionContext;
import com.zhuozhengsoft.pageoffice.FileSaver;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.Template;
import edu.iec.oa.util.HqlHelper;

@Controller
@Scope("prototype")
public class TemplateAction extends ModelDrivenBaseAction<Template> {
	private static final long serialVersionUID = 1L;
	
	private File upload; 				// 上传的文件
	private InputStream inputStream; 	// 下载用的
	private String Vname;

	/** 列表 */
	public String list() throws Exception {
//		List<Template> templateList = templateService.findAll();
//		ActionContext.getContext().put("templateList", templateList);
		
		//构建查询条件
		new HqlHelper(Template.class, "t")
			//过滤条件
			.addWhereCondition(Vname != null && Vname.length() > 0, "t.name LIKE ?", "%"+Vname+"%")//姓名
			.addOrderByProperty("t.id", true)//排序：id升序
			.preparePageBean(templateService, pageNum);
		
		return "list";
	}

	/**删除 */
	public String delete() throws Exception {
		templateService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备数据：processDefinitionList
		List<ProcessDefinition> processDefinitionList = processDefinitionService.findAllLatestVersionList();
		ActionContext.getContext().put("processDefinitionList", processDefinitionList);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		if(upload != null){
			String path = saveUploadFile(upload);	// 处理上传的文件
			model.setPath(path);
		}
		templateService.save(model);			// 调用业务方法（保存）
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备回显的数据
		Template template = templateService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(template);//放到栈顶
		
		// 准备数据：processDefinitionList：让模板选择对应的流程
		List<ProcessDefinition> processDefinitionList = processDefinitionService.findAllLatestVersionList();
		ActionContext.getContext().put("processDefinitionList", processDefinitionList);
	
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中获取原对象
		Template template = templateService.getById(model.getId());
		
		// 2，设置要修改的属性
		template.setName(model.getName());
		template.setProcessKey(model.getProcessKey());
		if(upload != null){ // 如果上传了文件，才表示要修改文件模板内容：表示添加了新的模板
			// 删除老文件
			if(template.getPath() != null && !template.getPath().equals("")){
				File file = new File(template.getPath());
				if(file.exists()){
					file.delete();
				}
			}
			// 保存新文件
			String path = saveUploadFile(upload);
			template.setPath(path);
		}
		
		// 3，更新到数据库中
		templateService.update(template);
		
		return "toList";
	}

	/** 下载 */
	public String download() throws Exception {
		// 获取模板对象的信息
		Template template = templateService.getById(model.getId());
		String path = template.getPath();

		if(path != null && !path.equals("")){
			// 准备默认的文件名：这里的文件名不是文件的真实名称，而是模板名称
			String fileName = template.getName();
			fileName = URLEncoder.encode(fileName, "utf-8"); // 解决下载的文件名的乱码问题
			ActionContext.getContext().put("fileName", fileName);//放在值栈中，通过strut2配置文件去名称
			// 准备要下载的数据
			inputStream = new FileInputStream(path);
			return "download";
		}else{
			return "notFondFile";
		}
	}
	
	/**在线查看word*/
	public String showOnline() throws Exception {
		Template template = templateService.getById(model.getId());
		String path = template.getPath();
		if(path != null && !path.equals("")){
			ServletActionContext.getRequest().setAttribute("path", path);
			return "showOnline";
		}else{
			return "notFondFile";
		}
	}
	
	/**在线编辑*/
	public String editOnline() throws Exception {
		Template template = templateService.getById(model.getId());
		String path = template.getPath();
		if(path != null && !path.equals("")){
			ServletActionContext.getRequest().setAttribute("path", path);
			ServletActionContext.getRequest().setAttribute("id", model.getId());
			return "editOnline";
		}else{
			return "notFondFile";
		}
	}
	/**在线编辑保存*/
	public String saveTeplate() throws Exception {
		Long id =Long.valueOf(getRequest().getParameter("id")) ;
		System.out.println("id=========="+id);
		Template template = templateService.getById(id);
		String path = template.getPath();
		
		FileSaver save = new FileSaver(getRequest(),getResponse());
		save.saveToFile(path);
		save.close();
		return NONE;
	}
	
	
	// =========================
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getVname() {
		return Vname;
	}

	public void setVname(String vname) {
		Vname = vname;
	}
}
