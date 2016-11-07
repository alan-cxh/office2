package edu.iec.oa.domain;

import org.jbpm.api.task.Task;

/**
 * 在页面中显示用的JavaBean。
 * 表示一个任务的信息
 * 任务本身的信息 + 与任务相关联的申请的信息
 */
public class TaskView {
	
	private Task task;				//任务
	private Application application;//流转的申请表单
	
	public TaskView() {
	}

	public TaskView(Task task, Application application) {
		this.task = task;
		this.application = application;
	}

	
	//===========================
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

}
