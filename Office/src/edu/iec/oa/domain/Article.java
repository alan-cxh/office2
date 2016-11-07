package edu.iec.oa.domain;

import java.util.Date;

/**
 * @author TaoJG
 * 文章：将（主题）主贴和（回复）跟帖相同的属性抽取到文章（Article）里面
 */
public class Article {
	
	private Long id;			//文章id
	private String title;		//文章标题
	private String content;		//文章内容
	private User author;		//发表作者
	private Date postTime;		//发表时间
	private String ipAddr;		//发表文章用户的IP地址
	
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
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
}
