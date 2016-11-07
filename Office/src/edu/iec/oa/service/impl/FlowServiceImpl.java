package edu.iec.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.jbpm.api.Execution;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iec.oa.domain.Application;
import edu.iec.oa.domain.ApproveInfo;
import edu.iec.oa.domain.TaskView;
import edu.iec.oa.domain.User;
import edu.iec.oa.service.FlowService;

/**
 * @author TaoJG
 * 开事务
 * IOC注入
 */
@Service
@Transactional
public class FlowServiceImpl implements FlowService {

	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private ProcessEngine processEngine;//从IOC容器中获得流程引擎

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 提交申请：开始流转
	 */
	public void submit(Application application) {
		// 1，设置属性并保存申请信息
		application.setApplyTime(new Date()); // 申请时间
		application.setStatus(Application.STATUS_RUNNING); // 状态，默认为“正在审批中”
		
		application.setTitle(application.getTemplate().getName()// 标题格式：{模版名称}_{申请人姓名}_{申请日期}
				+ "_" + application.getApplicant().getName()//
				+ "_" + sdf.format(application.getApplyTime()));
		
		// 保存
		sessionFactory.getCurrentSession().save(application);

		// 2，启动流程开始流转
		// 启动流程实例，设置流程变量application
		String processKey = application.getTemplate().getProcessKey(); // 流程定义的key：中申请表单所属的模板中获取
		
		Map<String, Object> variables = new HashMap<String, Object>(); // 流程变量
		variables.put("application", application);
		// 启动流程实例，设置流程变量,在后面的代办事项（我的任务列表）中，需要查看任务且带有每个任务的申请信息
		ProcessInstance pi = processEngine.getExecutionService().startProcessInstanceByKey(processKey, variables);

		//办理完第一个任务（提交申请）
		Task task = processEngine.getTaskService()//获取TaskAervice 的API
			.createTaskQuery()// 查询出本流程实例中当前情况下仅有的一个任务
			.processInstanceId(pi.getId())//过滤条件
			.uniqueResult();
		
		processEngine.getTaskService().completeTask(task.getId());
	}

	/**
	 * 查询我的任务列表
	 */
	public List<TaskView> findMyTaskViewList(User currentUser) {
		// 查询我的任务列表
		String userId = currentUser.getLoginName(); // 使用loginName作为JBPM中的用户标识符
		List<Task> taskList = processEngine.getTaskService().findPersonalTasks(userId);//根据用户的登录名最为查找个人任务列表的条件
		List<TaskView> taskViewList = new ArrayList<TaskView>();// 获取每个任务对应的申请信息
		for (Task task : taskList) {
			Application application = (Application) processEngine.getTaskService()
					.getVariable(task.getId(),"application");
			TaskView taskView = new TaskView(task, application);
			taskViewList.add(taskView);
		}
		return taskViewList;
	}

	
	/**
	 * 根据申请id获得申请信息
	 */
	public Application getApplicationById(Long applicationId) {
		return (Application) sessionFactory//
			.getCurrentSession()//
			.get(Application.class, applicationId);
	}

	/**
	 * 审批处理
	 */
	public void approve(ApproveInfo approveInfo, String taskId, String outcome) {
		// 1, 保存审批信息
		sessionFactory.getCurrentSession().save(approveInfo);

		// 2, 根据当前的任务id找到当前节点的任务，让任务完成后继续向下执行
		Task task = processEngine.getTaskService().getTask(taskId); // 获取任务的代码一定要是写到完成任务前，因为任务办理完后就变成了历史任务信息。
		
		//判断流程的类型  直走还是分支：完成任务
		if (outcome == null) {
			processEngine.getTaskService().completeTask(taskId); // 使用默认的Transition
		} else {
			processEngine.getTaskService().completeTask(taskId, outcome); // 使用指定名称的Transition
		}

		// 获取任务所属的流程实例（正在执行的表中的信息），如果流程已经结束了，则返回null
		ProcessInstance pi = processEngine.getExecutionService().findProcessInstanceById(task.getExecutionId());

		// 3, 维护申请的状态
		Application application = approveInfo.getApplication();
		// 如果本环节不同意，则流程直接结束，申请的状态为“未通过”
		if ( !approveInfo.isApproval() ) {
			// 如果本环节不是最后一个，我们就要手工结束这个流程实例
			if (pi != null) {//手动结束流程实例
				processEngine.getExecutionService().endProcessInstance(pi.getId(), Execution.STATE_ENDED);
			}
			//申请的状态为“未通过”
			application.setStatus(Application.STATUS_REJECTED);
		} else {
			if (pi == null) {
				// 如果本环节同意了，并且本环节是最后一个审批，则流程正常结束，申请的状态为“已通过” （相当于每个环节都同意了，且流程实例结束了：表示通过）
				application.setStatus(Application.STATUS_APPROVED);
			}
		}
		//更新申请
		sessionFactory.getCurrentSession().update(application);
	}

	public Collection<String> getOutcomesByTaskId(String taskId) {
		return processEngine.getTaskService().getOutcomes(taskId);
	}

	@SuppressWarnings("unchecked")
	public List<ApproveInfo> getApproveInfosByApplicationId(Long applicationId) {
		return sessionFactory.getCurrentSession()//
			.createQuery("FROM ApproveInfo a WHERE a.application.id=? ORDER BY a.approveTime ASC")//
			.setParameter(0, applicationId)//
			.list();
	}
}
