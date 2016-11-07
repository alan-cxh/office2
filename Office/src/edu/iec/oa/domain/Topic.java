package edu.iec.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author TaoJG
 * 主题(主贴)，每个版块里面的主贴
 */

public class Topic extends Article{
	/**普通帖*/
	public static final int TYPE_NORMAL = 0;
	/**精华帖*/
	public static final int TYPE_BEST = 1;
	/**置顶帖*/
	public static final int TYPE_TOP = 2;
	
	private int type;									//主贴类型
	private int replyCount;								//每个主贴的回复数量
	private Date lastUpdateTime;						//最后更新时间
	
	private Forum forum;								//所属版块：对多一
	private Set<Reply> replies = new HashSet<Reply>();	//（主题）主贴对应的回复数：一对多
	
	//private Reply lastReply;							//最后的回复
	
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public Set<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
