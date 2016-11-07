package edu.iec.oa.domain;
import java.util.Date;

/**
 * 审批信息
 */
public class ApproveInfo {
	
	private Long id;
	private Date approveTime;			// 审批时间
	private boolean approval; 			// 是否通过
	private String comment; 			// 审批意见

	private User approver;				// 审批人：审批信息与用户的多对一
	private Application application; 	// 申请：审批信息与申请的多对一
	
	//==============================
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
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

}
