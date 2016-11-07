package edu.iec.oa.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * @author TaoJG
 * 公告
 */
public class Notice implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;			//公告编号
	private String title;		//公告标题
	private String content;		//公告内容
	private User publishUser;	//公告发布人， 公告与用户的多对一关系
	private Date publishDate;	//公告发布时间
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getPublishUser() {
		return publishUser;
	}
	public void setPublishUser(User publishUser) {
		this.publishUser = publishUser;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
}
