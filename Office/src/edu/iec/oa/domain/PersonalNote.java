package edu.iec.oa.domain;

import java.util.Date;

/**
 * @author TaoJG
 * 工作日记：个人日记
 */
public class PersonalNote {
	
	private Long id;			//编号
	private String title;		//标题
	private String content;		//内容
	private String author;		//作者：从session中获取，不用与User对象关联了
	private Date time;			//创建时间
	
	//get and set
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
