package edu.iec.oa.view.action;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.BaseAction;
import edu.iec.oa.domain.Application;
import edu.iec.oa.domain.ApproveInfo;
import edu.iec.oa.domain.TaskView;
import edu.iec.oa.domain.Template;
import edu.iec.oa.util.HqlHelper;


/**
 * @author TaoJG
 * 流转功能
 */
@Controller
@Scope("prototype")
public class FlowAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private Long templateId;	//选择表单时的表单Id
	private File upload;		//上传的文件：申请人填写的表单文档
	private Long applicationId;	//查看我的申请需要需要的参数
	private String status;		//查询条件：状态
	private String taskId;		//任务id：处理我的任务列表里面的任务的时候需要传taskId参数
	
	private boolean approval;	//审批页面的参数，是否同意
	private String comment;		//审批页面的参数，处理意见
	private String outcome;		//审批页面的参数，处理意见
	
	// =========================== 申请人的功能 =================================
	
	/** 起草申请（申请模版列表） */
	public String templateList() throws Exception {
		List<Template> templateList =  templateService.findAll();//查找所有的模板
		ActionContext.getContext().put("templateList", templateList);//放到值栈中
		return "templateList";
	}
	
	/** 提交申请页面 */
	public String submitUI() throws Exception {
		return "submitUI";
	}
	
	/** 提交申请 */
	public String submit() throws Exception {
		//封装对象
		Application application = new Application();
		//页面上的参数：在action中封装
		application.setTemplate(templateService.getById(templateId));
		application.setPath(saveUploadFile(upload));
		
		//显示层的信息
		application.setApplicant(getCurrentUser());
		
		// 调用业务方法（保存申请信息，启动流程开始流转）
		flowService.submit(application);
		
		return "toMyApplicationList"; // 转到我的申请查询
	}
	
	/** 我的申请查询 */
	public String myApplicationList() throws Exception {
		
		//准备分页信息
		new HqlHelper(Application.class, "a")//
			.addWhereCondition("a.applicant=?", getCurrentUser())// 申请人是当前登录用户
			.addWhereCondition((templateId != null), "a.template.id=?", templateId)//过滤条件：根据模板查找
			.addWhereCondition((status != null && status.trim().length() > 0), "a.status=?", status)//过滤条件：根据状态查找
			.addOrderByProperty("a.applyTime", false)//过滤条件：根据申请时间降序
			.preparePageBean(userService, pageNum); // 注意service要是一个继承了DaoSupportImpl的类：因为flowService没又继承DaoSupport实现类，不能传flowService
		
		//页面上的准备数据:查找所有模板
		List<Template> templateList = templateService.findAll();
		ActionContext.getContext().put("templateList", templateList);
		
		return "myApplicationList";
	}
	
	
	// =========================== 审批人的功能 =================================
	/** 待我审批 */
	public String myTaskList() throws Exception {
		//由于待我审批中包含了Task的信息和Application的信息，所以将两个封装到TaskView对象里面
		List<TaskView> taskViewList = flowService.findMyTaskViewList(getCurrentUser());
		ActionContext.getContext().put("taskViewList", taskViewList);
		
		return "myTaskList";
	}
	/** 审批处理页面 */
	public String approveUI() throws Exception {
		// 准备数据
		Collection<String> outcomes = flowService.getOutcomesByTaskId(taskId);
		ActionContext.getContext().put("outcomes", outcomes);
		return "approveUI";
	}
	/** 审批处理 */
	public String approve() throws Exception {
		// 封装对象
		ApproveInfo approveInfo = new ApproveInfo();
		approveInfo.setApproval(approval);
		approveInfo.setComment(comment);
		
		approveInfo.setApplication(flowService.getApplicationById(applicationId));
		approveInfo.setApprover(getCurrentUser()); 		// 审批人，当前登录用户
		approveInfo.setApproveTime(new Date()); 		// 审批时间，当前时间
		
		
		// 调用业务方法（保存审批信息，完成当前任务，维护申请的状态）
		flowService.approve(approveInfo, taskId, outcome);
		
		return "toMyTaskList"; // 转到待我审批
	}
	
	/** 查看流转记录 */
	public String approvedHistory() throws Exception {
		//根据申请id得到申请信息
		List<ApproveInfo> approveInfoList = flowService.getApproveInfosByApplicationId(applicationId);
		ActionContext.getContext().put("approveInfoList", approveInfoList);
		return "approvedHistory";
	}

	/** 查看申请信息 */
	public String applicationInfo() throws Exception {
		//根据申请id得到申请信息
		Application application = flowService.getApplicationById(applicationId);
		ActionContext.getContext().getValueStack().push(application);
		return "applicationInfo";
	}
	
	///=========================================
	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
}
